package com.dcits.rpc.consumer.core;

import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.type.AnnotationMetadata;

/**
 * <p>Title: PropertiesResolver</p>
 * <p>Description: </p>
 *
 * @author zhao.xiaobo
 * @version 3.0.0
 * @date 2020-03-11 16:28
 */
public interface ConsumerResolver {

    Map<String, Object> getProperties(AnnotationMetadata annotationMetadata);

    void registerConsumer(BeanDefinitionRegistry registry, AnnotationMetadata annotationMetadata, Map<String, Object> attributes);

    default void registerConsumer(BeanDefinitionRegistry registry,BeanDefinition beanDefinition){

   }

    default void registerConsumer(BeanDefinitionRegistry registry, BeanDefinition beanDefinition, BeanFactory beanFactory){

    }

}
