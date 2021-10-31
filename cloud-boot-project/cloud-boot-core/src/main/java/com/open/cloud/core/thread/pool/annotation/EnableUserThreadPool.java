package com.open.cloud.core.thread.pool.annotation;

import com.open.cloud.core.thread.pool.TheadBeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

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
