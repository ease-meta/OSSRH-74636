package com.open.cloud.core.thread.pool.annotation;

import com.open.cloud.core.thread.pool.TheadBeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Leijian
 * @date 2020/5/23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(TheadBeanDefinitionRegistryPostProcessor.class)
public @interface EnableUserThreadPool {
}
