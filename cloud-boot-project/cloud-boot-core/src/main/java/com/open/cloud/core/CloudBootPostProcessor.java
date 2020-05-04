package com.open.cloud.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 *
 * @author Leijian
 * @date   2020/5/4 22:08
 */
public class CloudBootPostProcessor implements BeanPostProcessor {

    public CloudBootPostProcessor() {
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
                                                                               throws BeansException {

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
                                                                              throws BeansException {

        return bean;
    }
}
