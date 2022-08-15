package io.github.meta.ease.cache.beans.factory.config;

import io.github.meta.ease.cache.annotation.CacheExpire;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * <p>文件名称:  IntegrationCacheInterceptor</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/8/15</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
@Slf4j
public class IntegrationCacheInterceptor extends CacheInterceptor {
    private final CacheInterceptor cacheInterceptor;

    public IntegrationCacheInterceptor(CacheInterceptor cacheInterceptor) {
        this.cacheInterceptor = cacheInterceptor;
    }

    public Object invoke(final MethodInvocation invocation) throws Throwable {
        return cacheInterceptor.invoke(invocation);
    }

    private long parseCacheExpire(CacheOperationInvocationContext<?> context) {
        Method method = context.getMethod();
        // 方法上是否标注了CacheExpire
        if (AnnotatedElementUtils.isAnnotated(method, CacheExpire.class)) {
            // 获取对象
            CacheExpire cacheExpire = AnnotationUtils.getAnnotation(method, CacheExpire.class);
            assert cacheExpire != null;
            log.trace("CacheExpire ttl:{}, CacheExpire unit:{}", cacheExpire.ttl(), cacheExpire.unit());
            return Duration.ofMillis(cacheExpire.unit().toMillis(cacheExpire.ttl())).toMillis();
        }
        return 0L;
    }
}
