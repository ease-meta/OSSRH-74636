package io.github.meta.ease.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * <p>文件名称:  CacheExpire</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/8/12</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheExpire {
    /**
     * 失效时间，默认是60
     */
    long ttl() default 60L;

    /**
     * 单位，默认是秒
     */
    TimeUnit unit() default TimeUnit.SECONDS;
}
