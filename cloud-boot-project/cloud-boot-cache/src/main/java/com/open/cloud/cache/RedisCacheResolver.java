package com.open.cloud.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.AbstractCacheResolver;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Leijian
 * @date 2020/4/21 23:15
 */
public class RedisCacheResolver extends AbstractCacheResolver {

    public RedisCacheResolver(CacheManager cacheManager) {
        super(cacheManager);
    }


    static RedisCacheResolver of(CacheManager cacheManager) {
        return (cacheManager != null ? new RedisCacheResolver(cacheManager) : null);
    }

    @Override
    protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        return context.getOperation().getCacheNames();
    }

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        Collection<String> cacheNames = getCacheNames(context);
        if (cacheNames == null) {
            return Collections.emptyList();
        }
        Collection<Cache> result = new ArrayList<Cache>(cacheNames.size());
        for (String cacheName : cacheNames) {
            if (StringUtils.hasText(cacheName)) {
                throw new IllegalArgumentException("Cached name cannot be empty");
            }
            if (cacheName.startsWith(CacheConstant.PARAM) || cacheName.startsWith(CacheConstant.BUSINESS)) {
                Cache cache = getCacheManager().getCache(cacheName);
                if (cache == null) {
                    throw new IllegalArgumentException("Cannot find cache named '" +
                            cacheName + "' for " + context.getOperation());
                }
                result.add(cache);
            } else {
                throw new IllegalArgumentException("Cannot find cache named '" +
                        cacheName + "' for " + context.getOperation());
            }
        }
        return result;
    }
}
