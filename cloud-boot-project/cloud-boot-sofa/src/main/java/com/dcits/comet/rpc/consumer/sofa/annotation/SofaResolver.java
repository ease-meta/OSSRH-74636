package com.dcits.comet.rpc.consumer.sofa.annotation;

import com.alipay.sofa.runtime.api.ServiceRuntimeException;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import com.alipay.sofa.runtime.api.binding.BindingType;
import com.alipay.sofa.runtime.service.impl.BindingConverterFactoryImpl;
import com.alipay.sofa.runtime.spi.binding.Binding;
import com.alipay.sofa.runtime.spi.service.BindingConverter;
import com.alipay.sofa.runtime.spi.service.BindingConverterContext;
import com.alipay.sofa.runtime.spi.service.BindingConverterFactory;
import com.alipay.sofa.runtime.spring.factory.ReferenceFactoryBean;
import com.alipay.sofa.runtime.spring.parser.AbstractContractDefinitionParser;
import com.dcits.rpc.consumer.core.AbstractConsumerResolver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * <p>Title: SofaResolver</p>
 * <p>Description: </p>
 *
 * @author zhao.xiaobo
 * @version 3.0.0
 * @date 2020-03-13 09:23
 */
public class SofaResolver extends AbstractConsumerResolver {

    private CometSofaConsumer cometSofaConsumer;

    private final BindingConverterFactory bindingConverterFactory;

    @CometSofaConsumer
    private final class EmptyClazz {

    }

    public SofaResolver(Environment environment) {
        super(environment);
        cometSofaConsumer = EmptyClazz.class.getAnnotation(CometSofaConsumer.class);
        bindingConverterFactory = new BindingConverterFactoryImpl();
        bindingConverterFactory.addBindingConverters(getClassesByServiceLoader(BindingConverter.class));
    }

    @Override
    public Map<String, Object> getProperties(AnnotationMetadata annotationMetadata) {
        Map result = annotationMetadata.getAnnotationAttributes(CometSofaConsumer.class.getCanonicalName());
        if (result == null) {
            result = new HashMap<String, Object>();
        }
        result.put("uniqueId", cometSofaConsumer.uniqueId());
        result.put("binding", cometSofaConsumer.binding());
        return result;
    }

    @Override
    public void registerConsumer(BeanDefinitionRegistry registry, AnnotationMetadata annotationMetadata, Map<String, Object> attributes) {
        registrySofaConsumer(registry, annotationMetadata, attributes);
    }

    private void registrySofaConsumer(BeanDefinitionRegistry registry,
            AnnotationMetadata annotationMetadata, Map<String, Object> attributes) {
        String className = annotationMetadata.getClassName();

        BeanDefinitionBuilder definition = BeanDefinitionBuilder
                .genericBeanDefinition(ReferenceFactoryBean.class);

        definition.addPropertyValue(AbstractContractDefinitionParser.UNIQUE_ID_PROPERTY, attributes.get("uniqueId"));
        try {
            attributes.put("value", className);
            definition.addPropertyValue(AbstractContractDefinitionParser.INTERFACE_CLASS_PROPERTY,
                    Class.forName((String) attributes.get("value")));
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("@CometConsumer must on interface");
        }
        definition.addPropertyValue(AbstractContractDefinitionParser.BINDINGS,
                getSofaReferenceBinding(attributes));
        definition.addPropertyValue(AbstractContractDefinitionParser.DEFINITION_BUILDING_API_TYPE,
                true);
        AbstractBeanDefinition beanDefinition = definition.getBeanDefinition();

        boolean primary = (Boolean) attributes.get("jvmFirst"); // has a default, won't be

        beanDefinition.setPrimary(!primary);

        BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, className);
        BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
    }

    /**
     * get sofa reference binding annotated on parameter. At present, only jvm sofa reference is supported .
     */
    private List<Binding> getSofaReferenceBinding(Map<String, Object> attributes
    ) {
        SofaReferenceBinding sofaReferenceBinding = (SofaReferenceBinding) attributes.get("binding");
        List<Binding> bindings = new ArrayList<>();
        BindingConverter bindingConverter = bindingConverterFactory
                .getBindingConverter(new BindingType(sofaReferenceBinding.bindingType()));
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

    public static <T> Set<T> getClassesByServiceLoader(Class<T> clazz) {
        ServiceLoader<T> serviceLoader = ServiceLoader.load(clazz);

        Set<T> result = new HashSet<>();
        for (T t : serviceLoader) {
            result.add(t);
        }
        return result;
    }

}
