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
package com.open.cloud.cache.autoconfigure;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.open.cloud.cache.NotThrowCacheErrorHandler;
import com.open.cloud.cache.properties.CacheProperties;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.CacheAspectSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/10/30 18:58
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(CacheManager.class)
@ConditionalOnBean(CacheAspectSupport.class)
@ConditionalOnMissingBean(value = CacheManager.class)
@EnableConfigurationProperties(CacheProperties.class)
@AutoConfigureBefore(org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration.class)
public class CacheAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(CacheManager.class)
    @EnableConfigurationProperties(CacheProperties.class)
    @EnableCaching
    static class CaffeineCacheConfiguration {

        @Bean
        @ConditionalOnMissingBean(CacheManager.class)
        public CaffeineCacheManager caffeineCacheManager(CacheProperties cacheProperties,
                                                         CacheAspectSupport cacheAspectSupport) {
            CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
            caffeineCacheManager.setAllowNullValues(false);
            Caffeine caffeine = Caffeine.newBuilder()
                    .expireAfterWrite(Duration.ofMillis(cacheProperties.getMillis())).recordStats();
            caffeineCacheManager.setCaffeine(caffeine);
            cacheAspectSupport.setErrorHandler(new NotThrowCacheErrorHandler());
            return caffeineCacheManager;
        }
    }
}
