package com.open.cloud.sofa.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class CloudBootBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor, BeanPostProcessor {
	private static final Logger logger = LoggerFactory.getLogger(CloudBootBeanFactoryPostProcessor.class);
	private BeanDefinitionRegistry registry;

	public CloudBootBeanFactoryPostProcessor() {
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {

	}


	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
	}
}
