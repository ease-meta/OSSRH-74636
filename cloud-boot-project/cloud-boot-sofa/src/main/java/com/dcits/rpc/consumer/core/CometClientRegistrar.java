package com.dcits.rpc.consumer.core;


import static com.dcits.rpc.consumer.core.CometConsumerProperties.RESOLVER_KEY;

import com.dcits.comet.rpc.api.annotation.CometConsumer;
import com.dcits.rpc.consumer.core.annotation.EnableCometClients;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
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
import org.springframework.util.StringUtils;

/**
 * <p>Title: CometClientRegistrar</p>
 * <p>Description: </p>
 *
 * @author zhao.xiaobo
 * @version 3.0.0
 * @date 2020-03-11 15:50
 */
public class CometClientRegistrar implements BeanFactoryAware, ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {
	// patterned after Spring Integration IntegrationComponentScanRegistrar
	// and RibbonClientsConfigurationRegistgrar
	private static final Logger logger = LoggerFactory.getLogger(CometClientRegistrar.class);

	private ResourceLoader resourceLoader;

	private Environment environment;

	private ConsumerResolver consumerResolver;

	private BeanFactory beanFactory;

	public CometClientRegistrar() {

	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
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
				.getAnnotationAttributes(EnableCometClients.class.getName());
		AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(
				CometConsumer.class);
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

		for (String basePackage : basePackages) {
			Set<BeanDefinition> candidateComponents = scanner
					.findCandidateComponents(basePackage);
			for (BeanDefinition candidateComponent : candidateComponents) {
				if (candidateComponent instanceof AnnotatedBeanDefinition) {
					// verify annotated class is an interface
					AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition) candidateComponent;
					AnnotationMetadata annotationMetadata = beanDefinition.getMetadata();
					Assert.isTrue(annotationMetadata.isInterface(),
							"@CometConsumer can only be specified on an interface");
					Map<String, Object> attributes = annotationMetadata
							.getAnnotationAttributes(
									CometConsumer.class.getCanonicalName());
					attributes.putAll(consumerResolver.getProperties(annotationMetadata));
					consumerResolver.registerConsumer(registry, annotationMetadata, attributes);
					//consumerResolver.registerConsumer(registry, beanDefinition, this.beanFactory);
				}
			}
		}
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
				.getAnnotationAttributes(EnableCometClients.class.getCanonicalName());

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
	public void setEnvironment(Environment environment) {
		this.environment = environment;
		Class clazz;
		try {
			clazz = Class.forName(environment.getProperty(RESOLVER_KEY));
		} catch (Exception e) {
			try {
				clazz = Class.forName(CometConsumerProperties.getResolverClazz());
			} catch (Exception ex) {
				throw new IllegalStateException("Can't find registrar by config.");
			}
		}
		Constructor constructor;
		if (ConsumerResolver.class.isAssignableFrom(clazz)) {
			try {
				constructor = clazz.getDeclaredConstructor(Environment.class);
				consumerResolver = (ConsumerResolver) constructor.newInstance(environment);
			} catch (Exception e) {
				throw new IllegalStateException("Client registrar must extend by AbstractConsumerResolver");
			}

		}
		if (consumerResolver == null) {
			throw new IllegalStateException("Can't init registrar by config");
		}
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
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
}
