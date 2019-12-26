package com.open.cloud.framework.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CaffeineCacheConfig {
	public static final Integer CAFFEINE_MAXSIZE = 10000;
	public static final Integer CAFFEINE_EXPIRE_TIME = 3;

	@Bean("caffeineCacheManager")
	public CacheManager CaffeineCacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager();
		cacheManager.setCaffeine(Caffeine.newBuilder().recordStats()
				.expireAfterWrite(CAFFEINE_EXPIRE_TIME, TimeUnit.SECONDS)
				.maximumSize(CAFFEINE_MAXSIZE));
		return cacheManager;
	}
}