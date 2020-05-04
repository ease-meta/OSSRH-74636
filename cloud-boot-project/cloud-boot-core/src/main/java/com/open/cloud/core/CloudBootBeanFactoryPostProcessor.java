package com.open.cloud.core;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 *
 * @author Leijian
 * @date   2020/5/4 22:07
 */
public class CloudBootBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public CloudBootBeanFactoryPostProcessor() {
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        System.out
            .println("【BeanFactoryPostProcessor接口】调用BeanFactoryPostProcessor接口的postProcessBeanFactory方法:"
                     + beanFactory);

    }
}
