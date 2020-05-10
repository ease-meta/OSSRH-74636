package com.dcits.comet.rpc.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CometConsumer {

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
