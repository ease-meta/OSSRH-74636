package com.dcits.rpc.consumer.core.annotation;

import com.dcits.rpc.consumer.core.CometClientRegistrar;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * <p>Title: EnableCometClients</p>
 * <p>Description: </p>
 *
 * @author zhao.xiaobo
 * @version 3.0.0
 * @date 2020-03-11 15:53
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(CometClientRegistrar.class)
public @interface EnableCometClients {

    String[] value() default {};

    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};

    Class<?>[] defaultConfiguration() default {};

    Class<?>[] clients() default {};
}
