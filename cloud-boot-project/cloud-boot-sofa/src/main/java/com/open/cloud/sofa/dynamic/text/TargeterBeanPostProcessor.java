package com.open.cloud.sofa.dynamic.text;

import com.alipay.sofa.runtime.spi.binding.BindingAdapterFactory;
import com.alipay.sofa.runtime.spi.component.SofaRuntimeContext;
import com.alipay.sofa.runtime.spi.service.BindingConverterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static org.springframework.util.ClassUtils.getUserClass;

/**
 *openfeign
 */
@Component
public class TargeterBeanPostProcessor implements BeanPostProcessor, BeanClassLoaderAware {

	private Logger logger = LoggerFactory.getLogger(TargeterBeanPostProcessor.class);

	private final Environment environment;

	private ClassLoader classLoader;

	protected final SofaRuntimeContext sofaRuntimeContext;

	protected final BindingConverterFactory bindingConverterFactory;
	/** binding adapter factory */
	protected final BindingAdapterFactory bindingAdapterFactory;

	public TargeterBeanPostProcessor(Environment environment, SofaRuntimeContext sofaRuntimeContext, BindingConverterFactory bindingConverterFactory, BindingAdapterFactory bindingAdapterFactory) {
		this.environment = environment;
		this.sofaRuntimeContext = sofaRuntimeContext;
		this.bindingConverterFactory = bindingConverterFactory;
		this.bindingAdapterFactory = bindingAdapterFactory;
	}


	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Class<?> beanClass = getUserClass(bean.getClass());
		logger.info("1：{},{},{}", bean.getClass(), beanClass, beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Class<?> beanClass = getUserClass(bean.getClass());
		logger.info("2：{},{},{}", bean.getClass(), beanClass, beanName);
		return bean;
	}
}