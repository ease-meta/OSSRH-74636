package com.open.cloud.cache.config;

import org.springframework.data.redis.cache.CacheKeyPrefix;

/**
 * @author Leijian
 * @date 2020/4/22
 */
public class RedisCacheKeyPrefix implements CacheKeyPrefix {

	private String delimiter;

	public RedisCacheKeyPrefix() {

	}

	public RedisCacheKeyPrefix(String delimiter) {
		this.delimiter = delimiter;
	}

	@Override
	public String compute(String cacheName) {
		return delimiter != null ? cacheName.concat(delimiter) : cacheName.concat(":");
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getDelimiter() {
		return delimiter;
	}
}
