/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.meta.ease.cache.redis;

import io.github.meta.ease.cache.CacheConstant;
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
            if (cacheName.startsWith(CacheConstant.PARAM)
                    || cacheName.startsWith(CacheConstant.BUSINESS)) {
                Cache cache = getCacheManager().getCache(cacheName);
                if (cache == null) {
                    throw new IllegalArgumentException("Cannot find cache named '" + cacheName
                            + "' for " + context.getOperation());
                }
                result.add(cache);
            } else {
                throw new IllegalArgumentException("Cannot find cache named '" + cacheName
                        + "' for " + context.getOperation());
            }
        }
        return result;
    }
}
