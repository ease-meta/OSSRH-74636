package com.open.cloud.core.runtime.api.annotation;

import org.springframework.core.annotation.AliasFor;

/**
 * 服务消费者
 * @author Leijian
 * @date 2020/5/5
 */
public @interface ConsumerReference {

	/**
	 * restful uri rpc interfaceType.getName()
	 */
	String value() default "";

	/**
	 * rpc jvmFirst restful primary is false invoke jvm service first
	 *
	 * @return is jvm first or not
	 */
	boolean jvmFirst() default true;

	Class<?> interfaceType() default void.class;
}
