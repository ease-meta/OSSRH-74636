package com.open.cloud.sofa.dynamic;


import com.alipay.sofa.runtime.api.ServiceRuntimeException;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import com.alipay.sofa.runtime.api.binding.BindingType;
import com.alipay.sofa.runtime.service.impl.BindingConverterFactoryImpl;
import com.alipay.sofa.runtime.spi.binding.Binding;
import com.alipay.sofa.runtime.spi.component.SofaRuntimeContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

import com.alipay.sofa.runtime.spi.service.BindingConverter;
import com.alipay.sofa.runtime.spi.service.BindingConverterContext;
import com.alipay.sofa.runtime.spi.service.BindingConverterFactory;
import com.alipay.sofa.runtime.spring.bean.SofaBeanNameGenerator;
import com.alipay.sofa.runtime.spring.factory.ReferenceFactoryBean;
import com.alipay.sofa.runtime.spring.parser.AbstractContractDefinitionParser;
import com.open.cloud.core.runtime.api.annotation.ConsumerReference;
import com.open.cloud.sofa.dynamic.text.BeanRegistrationUtil;
import com.open.cloud.sofa.dynamic.text.TargeterBeanPostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AbstractClassTestingTypeFilter;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class ReferenceRegistrar implements BeanFactoryAware, EnvironmentAware, ImportBeanDefinitionRegistrar, ResourceLoaderAware {
	// patterned after Spring Integration IntegrationComponentScanRegistrar
	// and RibbonClientsConfigurationRegistgrar
	private static final Logger logger = LoggerFactory.getLogger(ReferenceRegistrar.class);

	private ResourceLoader resourceLoader;

	private Environment environment;

	private BeanFactory beanFactory;

	private SofaRuntimeContext sofaRuntimeContext;

	private ApplicationContext applicationContext;

	@SofaReferenceBinding
	private final class EmptyClazz {

	}

	private SofaReferenceBinding sofaReferenceBinding;

	public ReferenceRegistrar() {

	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
		sofaReferenceBinding = EmptyClazz.class.getAnnotation(SofaReferenceBinding.class);
	}

	public Environment getEnvironment() {
		sofaReferenceBinding = EmptyClazz.class.getAnnotation(SofaReferenceBinding.class);
		return environment;
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata metadata,
										BeanDefinitionRegistry registry) {
		registerConsumerClients(metadata, registry);
	}

	public void registerConsumerClients(AnnotationMetadata metadata,
										BeanDefinitionRegistry registry) {

		ClassPathScanningCandidateComponentProvider scanner = getScanner();
		scanner.setResourceLoader(this.resourceLoader);
		if (!AutoConfigurationPackages.has(this.beanFactory)) {
			logger.debug("Could not determine auto-configuration package, automatic scanning disabled.");
			return;
		}

		logger.debug("Searching for mappers annotated with @CometConsumer");
		List<String> basePackages;

		Map<String, Object> attrs = metadata
				.getAnnotationAttributes(EnableReference.class.getName());
		AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(
				ConsumerReference.class);
		final Class<?>[] clients = attrs == null ? null
				: (Class<?>[]) attrs.get("clients");
		if (clients == null || clients.length == 0) {
			scanner.addIncludeFilter(annotationTypeFilter);
			basePackages = getBasePackages(metadata);
		} else {
			final Set<String> clientClasses = new HashSet<>();
			basePackages = new LinkedList<>();
			for (Class<?> clazz : clients) {
				basePackages.add(ClassUtils.getPackageName(clazz));
				clientClasses.add(clazz.getCanonicalName());
			}
			AbstractClassTestingTypeFilter filter = new AbstractClassTestingTypeFilter() {
				@Override
				protected boolean match(ClassMetadata metadata) {
					String cleaned = metadata.getClassName().replaceAll("\\$", ".");
					return clientClasses.contains(cleaned);
				}
			};
			scanner.addIncludeFilter(
					new AllTypeFilter(Arrays.asList(filter, annotationTypeFilter)));
		}
		/*BeanRegistrationUtil.registerBeanDefinitionIfNotExists(registry, SofaResolver.class.getName(),
				SofaResolver.class);*/
		BeanRegistrationUtil.registerBeanDefinitionIfNotExists(registry, TargeterBeanPostProcessor.class.getName(), TargeterBeanPostProcessor.class);
		for (String basePackage : basePackages) {
			Set<BeanDefinition> beanDefinitions = scanner
					.findCandidateComponents(basePackage);
			for (BeanDefinition candidateComponent : beanDefinitions) {
				if (candidateComponent instanceof AnnotatedBeanDefinition) {
					AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition) candidateComponent;
					String beanClassName = beanDefinition.getBeanClassName();

					AnnotationMetadata annotationMetadata = beanDefinition.getMetadata();
					Assert.isTrue(annotationMetadata.isInterface(),
							"@CometConsumer can only be specified on an interface");
					Map<String, Object> attributes = annotationMetadata
							.getAnnotationAttributes(
									ConsumerReference.class.getCanonicalName());
					//attributes.putAll(consumerResolver.getProperties(annotationMetadata));
					Class o = (Class) attributes.get("interfaceType");
					registerConsumer(registry, beanDefinition, this.beanFactory);
				}
			}
		}
	}

	private void registerConsumer(BeanDefinitionRegistry registry, AnnotatedBeanDefinition beanDefinition, BeanFactory beanFactory) {
		AnnotationMetadata annotationMetadata = ((AnnotatedBeanDefinition) beanDefinition).getMetadata();
		Map<String, Object> attributes = annotationMetadata
				.getAnnotationAttributes(
						ConsumerReference.class.getCanonicalName());
		String className = annotationMetadata.getClassName();
		try {
			attributes.put("value", className);
			SofaConsumer[] sofaConsumer = Class.forName(className).getAnnotationsByType(SofaConsumer.class);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("@CometConsumer must on interface");
		}
		String beanName = className;
		Map<String, Object> cometConsumerAttrs = annotationMetadata
				.getAnnotationAttributes(ConsumerReference.class.getName());
		attributes.putAll(cometConsumerAttrs);
		Class o = (Class) cometConsumerAttrs.get("interfaceType");
		if (!o.equals(void.class)) {
			className = o.getName();
		}
		String referenceId;
		String uniqueId = "";
		Map<String, Object> cometSofaConsumerAttrs = annotationMetadata
				.getAnnotationAttributes(SofaConsumer.class.getName());
		attributes.putAll(cometSofaConsumerAttrs);

		if (!CollectionUtils.isEmpty(cometSofaConsumerAttrs)) {
			o = (Class) cometSofaConsumerAttrs.get("interfaceType");
			if (!o.equals(void.class)) {
				className = o.getName();
			}
			if (cometSofaConsumerAttrs.containsKey("uniqueId")) {
				uniqueId = String.valueOf(cometConsumerAttrs.get("uniqueId"));
			}
		}
		referenceId = SofaBeanNameGenerator.generateSofaReferenceBeanName(o,
				uniqueId);
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
		builder.getRawBeanDefinition().setScope(beanDefinition.getScope());
		builder.getRawBeanDefinition().setLazyInit(beanDefinition.isLazyInit());
		builder.getRawBeanDefinition().setBeanClass(ReferenceFactoryBean.class);
		builder.addPropertyValue(AbstractContractDefinitionParser.UNIQUE_ID_PROPERTY, uniqueId);
		builder.addPropertyValue(AbstractContractDefinitionParser.INTERFACE_CLASS_PROPERTY,
				o);
		builder.addPropertyValue(AbstractContractDefinitionParser.BINDINGS,
				getSofaReferenceBinding(attributes));
		builder.addPropertyValue(AbstractContractDefinitionParser.DEFINITION_BUILDING_API_TYPE,
				true);
		((BeanDefinitionRegistry) beanFactory).registerBeanDefinition(referenceId, builder.getBeanDefinition());
	}


	private List<Binding> getSofaReferenceBinding(Map<String, Object> attributes
	) {

		List<Binding> bindings = new ArrayList<>();
		BindingConverterFactory bindingConverterFactory = new BindingConverterFactoryImpl();
		bindingConverterFactory
				.addBindingConverters(getClassesByServiceLoader(BindingConverter.class));
		BindingConverter bindingConverter = bindingConverterFactory
				.getBindingConverter(new BindingType("bolt"));
		if (bindingConverter == null) {
			throw new ServiceRuntimeException("Can not found binding converter for binding type "
					+ sofaReferenceBinding.bindingType());
		}
		BindingConverterContext bindingConverterContext = new BindingConverterContext();
		bindingConverterContext.setInBinding(true);
		bindingConverterContext.setApplicationContext(new ClassPathXmlApplicationContext());
		bindingConverterContext.setAppName(getEnvironment().getProperty("spring.application.name"));
		bindingConverterContext.setAppClassLoader(this.getClass().getClassLoader());
		Binding binding = bindingConverter.convert(null, sofaReferenceBinding,
				bindingConverterContext);
		bindings.add(binding);
		return bindings;
	}

	protected ClassPathScanningCandidateComponentProvider getScanner() {
		return new ClassPathScanningCandidateComponentProvider(false, this.environment) {
			@Override
			protected boolean isCandidateComponent(
					AnnotatedBeanDefinition beanDefinition) {
				boolean isCandidate = false;
				if (beanDefinition.getMetadata().isIndependent()) {
					if (!beanDefinition.getMetadata().isAnnotation()) {
						isCandidate = true;
					}
				}
				return isCandidate;
			}
		};
	}

	protected List<String> getBasePackages(AnnotationMetadata importingClassMetadata) {
		Map<String, Object> attributes = importingClassMetadata
				.getAnnotationAttributes(EnableReference.class.getCanonicalName());

		List<String> basePackages = new LinkedList<>();
		for (String pkg : (String[]) attributes.get("value")) {
			if (StringUtils.hasText(pkg)) {
				basePackages.add(pkg);
			}
		}
		for (String pkg : (String[]) attributes.get("basePackages")) {
			if (StringUtils.hasText(pkg)) {
				basePackages.add(pkg);
			}
		}
		for (Class<?> clazz : (Class[]) attributes.get("basePackageClasses")) {
			basePackages.add(ClassUtils.getPackageName(clazz));
		}

		if (basePackages.isEmpty()) {
			basePackages = AutoConfigurationPackages.get(this.beanFactory);
			if (logger.isDebugEnabled()) {
				basePackages.forEach(pkg -> logger.debug("Using auto-configuration base package '{}'", pkg));
			}
		}
		return basePackages;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	/**
	 * Helper class to create a {@link TypeFilter} that matches if all the delegates match.
	 *
	 * @author Oliver Gierke
	 */
	private static class AllTypeFilter implements TypeFilter {

		private final List<TypeFilter> delegates;

		/**
		 * Creates a new {@link AllTypeFilter} to match if all the given delegates match.
		 *
		 * @param delegates must not be {@literal null}.
		 */
		AllTypeFilter(List<TypeFilter> delegates) {
			Assert.notNull(delegates, "This argument is required, it must not be null");
			this.delegates = delegates;
		}

		@Override
		public boolean match(MetadataReader metadataReader,
							 MetadataReaderFactory metadataReaderFactory) throws IOException {

			for (TypeFilter filter : this.delegates) {
				if (!filter.match(metadataReader, metadataReaderFactory)) {
					return false;
				}
			}

			return true;
		}

	}

	private static <T> Set<T> getClassesByServiceLoader(Class<T> clazz) {
		ServiceLoader<T> serviceLoader = ServiceLoader.load(clazz);

		Set<T> result = new HashSet<>();
		for (T t : serviceLoader) {
			result.add(t);
		}
		return result;
	}
}
