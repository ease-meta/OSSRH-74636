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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Leijian
 * @date 2020/4/21 22:30
 */
@Configuration
public class SpringDataRedisCacheConfig extends CachingConfigurerSupport {

	private Map<String, String> keyMap = new ConcurrentHashMap<String, String>();

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private RedisTemplate redisTemplate;

	@Bean
	@Override
	public CacheResolver cacheResolver() {
		return RedisCacheResolver.of(this.cacheManager);
	}

	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return (target, method, params) -> {
			StringBuilder sb = new StringBuilder();
			String packageName = target.getClass().getPackage().getName();
			if (StringUtils.isNotEmpty(packageName)) {
				if (keyMap.containsKey(packageName)) {
					sb.append(keyMap.get(packageName));
				} else {
					String[] strs = packageName.split("\\.", 15);
					StringBuilder sb1 = new StringBuilder();
					for (String str1 : strs) {
						sb1.append(str1.substring(0, 1)).append(".");
					}
					keyMap.put(packageName, sb1.toString());
					sb.append(sb1.toString());
				}
			}
			sb.append(target.getClass().getSimpleName());
			sb.append(":" + method.getName());
			for (Object obj : params) {
				sb.append("_");
				sb.append(obj);
			}
			return sb.toString();
		};
	}

	@Bean
	@Override
	public CacheErrorHandler errorHandler() {
		return new NotThrowCacheErrorHandler(redisTemplate);
	}
}