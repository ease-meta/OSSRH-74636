package com.open.cloud.sofa.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.env.Environment;

import static org.springframework.util.ClassUtils.getUserClass;

/**
 *openfeign
 */
public class TargeterBeanPostProcessor implements BeanPostProcessor, BeanClassLoaderAware {

	private Logger logger = LoggerFactory.getLogger(TargeterBeanPostProcessor.class);

	private final Environment environment;

	private ClassLoader classLoader;

	public TargeterBeanPostProcessor(Environment environment) {
		this.environment = environment;
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Class<?> beanClass = getUserClass(bean.getClass());
		logger.info("{},{},{}",bean.getClass(),beanClass,beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Class<?> beanClass = getUserClass(bean.getClass());
		logger.info("{},{},{}",bean.getClass(),beanClass,beanName);
		return bean;
	}
}