package com.open.cloud.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

/**
 *
 * @author Leijian
 * @date   2020/5/4 22:09
 */
public class CloudBootInstantiationAwareBeanPostProcessor extends
                                                         InstantiationAwareBeanPostProcessorAdapter {

    public CloudBootInstantiationAwareBeanPostProcessor() {
    }

    @Override
    public Object postProcessBeforeInstantiation(Class beanClass, String beanName)
                                                                                  throws BeansException {
        return beanClass;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
                                                                              throws BeansException {
        return bean;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds,
                                                    Object bean, String beanName)
                                                                                 throws BeansException {
        return pvs;
    }
}
