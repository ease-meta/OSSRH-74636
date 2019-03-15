package com.open.cloud.workflow.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.open.cloud.workflow.common.enums.AuthTypeEnum;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Resources {

	/**
	 * 权限认证类型
	 *
	 * @see com.open.cloud.workflow.common.enums.AuthTypeEnum
	 */
	AuthTypeEnum auth() default AuthTypeEnum.AUTH;

	String description() default "";
}