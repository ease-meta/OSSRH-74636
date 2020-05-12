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
        logger.info("【1BeanFactoryPostProcessor接口】调用BeanFactoryPostProcessor接口的postProcessBeanFactory方法:"
                     + beanFactory);

    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("【2BeanFactoryPostProcessor接口】调用BeanFactoryPostProcessor接口的postProcessBeanFactory方法:"
                        + beanName);
        return bean;
    }
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        this.registry = registry;
        logger.info("【3BeanFactoryPostProcessor接口】调用BeanFactoryPostProcessor接口的postProcessBeanFactory方法:"
                        + registry);
    }
}
