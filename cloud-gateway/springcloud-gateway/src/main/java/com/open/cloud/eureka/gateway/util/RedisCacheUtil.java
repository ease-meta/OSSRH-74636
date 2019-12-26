package com.open.cloud.eureka.gateway.util;

import com.alibaba.fastjson.JSON;
import io.lettuce.core.RedisException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class RedisCacheUtil {
	@Autowired
	private StringRedisTemplate redisTemplate;

	public Map<?, ?> getRedisCache(final String keyClass) {
		final Set limiterKeys = this.redisTemplate.opsForHash().keys(keyClass);
		final HashMap<String, Object> map = new HashMap<String, Object>();
		for (final Object key : limiterKeys) {
			map.put(key.toString(), this.redisTemplate.opsForHash().get(keyClass, key).toString());
		}
		return map;
	}

	public boolean saveRedisCache(final String keyClass, final String key, final Object object) {
		try {
			this.redisTemplate.opsForHash().put(keyClass, (Object) key, JSON.toJSON(object));
			return true;
		} catch (RedisException e) {
			RedisCacheUtil.log.error("redis存取失败", (Throwable) e);
			return false;
		}
	}

	public boolean delRedisCache(final String keyClass, final String key) {
		try {
			this.redisTemplate.opsForHash().delete(keyClass, new Object[]{key});
			return true;
		} catch (RedisException e) {
			RedisCacheUtil.log.error("redis删除失败", (Throwable) e);
			return false;
		}
	}
}
