package com.dcits.comet.rpc.consumer.sofa.annotation;

import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title: CometSofaConsumer</p>
 * <p>Description: </p>
 *
 * @author zhao.xiaobo
 * @version 3.0.0
 * @date 2020-03-13 09:21
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CometSofaConsumer {

    String uniqueId() default "";

    Class<?> interfaceType() default void.class;

    SofaReferenceBinding binding() default @SofaReferenceBinding(bindingType = "bolt");

}
