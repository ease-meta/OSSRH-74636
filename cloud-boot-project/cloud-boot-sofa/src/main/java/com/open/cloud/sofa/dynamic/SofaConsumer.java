package com.open.cloud.sofa.dynamic;

import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SofaConsumer {

    String uniqueId() default "";

    Class<?> interfaceType() default void.class;

    SofaReferenceBinding binding() default @SofaReferenceBinding(bindingType = "bolt");

}