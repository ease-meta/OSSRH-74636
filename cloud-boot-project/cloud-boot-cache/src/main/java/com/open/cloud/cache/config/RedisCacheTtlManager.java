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
package com.open.cloud.cache.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Leijian
 * @date 2020/4/22
 */
public class RedisCacheTtlManager extends RedisCacheManager {

    private Map<String, Long> expires = new ConcurrentHashMap<String, Long>();

    public RedisCacheTtlManager(RedisCacheWriter cacheWriter,
                                RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    public RedisCacheTtlManager(RedisCacheWriter cacheWriter,
                                RedisCacheConfiguration defaultCacheConfiguration,
                                String... initialCacheNames) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheNames);
    }

    public RedisCacheTtlManager(RedisCacheWriter cacheWriter,
                                RedisCacheConfiguration defaultCacheConfiguration,
                                boolean allowInFlightCacheCreation, String... initialCacheNames) {
        super(cacheWriter, defaultCacheConfiguration, allowInFlightCacheCreation, initialCacheNames);
    }

    public RedisCacheTtlManager(RedisCacheWriter cacheWriter,
                                RedisCacheConfiguration defaultCacheConfiguration,
                                Map<String, RedisCacheConfiguration> initialCacheConfigurations) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations);
    }

    public RedisCacheTtlManager(RedisCacheWriter cacheWriter,
                                RedisCacheConfiguration defaultCacheConfiguration,
                                Map<String, RedisCacheConfiguration> initialCacheConfigurations,
                                boolean allowInFlightCacheCreation) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations,
            allowInFlightCacheCreation);
    }

    @Override
    public Cache getCache(String name) {
        String[] cacheParams = name.split(CacheConstant.SEPARATOR);
        String cacheName = cacheParams[0];
        if (StringUtils.isBlank(cacheName)) {
            throw new IllegalArgumentException("Cached name cannot be empty");
        }
        if (!cacheName.startsWith(CacheConstant.PARAM)
            && !cacheName.startsWith(CacheConstant.BUSINESS)) {
            throw new IllegalArgumentException(
                "Cached names do not comply ,use 'param' or 'business'");
        }
        if (cacheParams.length > 1) {
            ExpressionParser parser = new SpelExpressionParser();
            EvaluationContext context = new StandardEvaluationContext();
            Long expirationSecondTime = parser.parseExpression(cacheParams[1]).getValue(context,
                Long.class);
            cacheName = cacheName + ":" + expirationSecondTime;
            expires.put(cacheName, expirationSecondTime);
        }
        return super.getCache(cacheName);
    }

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        long expirationSecondTime = computeExpiration(name);
        if (expirationSecondTime > 0) {
            RedisCacheKeyPrefix redisCacheKeyPrefix = new RedisCacheKeyPrefix();
            RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig().entryTtl(Duration.ofSeconds(expirationSecondTime))
                .computePrefixWith(redisCacheKeyPrefix).disableCachingNullValues();
            return super.createRedisCache(name, redisCacheConfiguration);
        } else {
            return super.createRedisCache(name, cacheConfig);
        }
    }

    protected long computeExpiration(String name) {
        Long expiration = null;
        if (expires != null) {
            expiration = expires.get(name);
        }
        return (expiration != null ? expiration.longValue() : 0L);
    }

}
