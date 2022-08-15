package io.github.meta.ease.cache.hazelcast.interceptor;

import io.github.meta.ease.cache.hazelcast.interceptor.SimpleCachingConfigurerSupport;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;

/**
 * <p>文件名称:  ExpireHazelcastSimpleCachingConfigurerSupport</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/8/12</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
public class ExpireHazelcastSimpleCachingConfigurerSupport extends SimpleCachingConfigurerSupport {
    private final CacheManager cacheManager;

    private final CacheResolver cacheResolver;

    private final KeyGenerator keyGenerator;

    public ExpireHazelcastSimpleCachingConfigurerSupport(CacheManager cacheManager, CacheResolver cacheResolver, ObjectProvider<KeyGenerator> keyGenerator) {
        this.cacheManager = cacheManager;
        this.cacheResolver = cacheResolver;
        this.keyGenerator = keyGenerator.getIfAvailable(SimpleKeyGenerator::new);
    }

    @Override
    public CacheManager cacheManager() {
        return cacheManager;
    }

    @Override
    public CacheResolver cacheResolver() {
        return cacheResolver;
    }

    @Override
    public KeyGenerator keyGenerator() {
        return keyGenerator;
    }
}
