package com.open.cloud.cache;

import org.springframework.context.annotation.Configuration;

/**
 * @author Leijian
 * @date 2020/4/21 22:29
 */
@Configuration
public class CacheConfiguration {


	/**
	 * 配置自定义redisTemplate
	 */
	/*@Bean
	@ConditionalOnMissingBean(RedisTemplate.class)
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory, RedisSerializer redisSerializer) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		template.setValueSerializer(redisSerializer);
		//使用StringRedisSerializer来序列化和反序列化redis的key值
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(redisSerializer);
		template.afterPropertiesSet();
		return template;
	}*/

	/**
	 * json序列化
	 */
	/*@Bean
	public RedisSerializer<Object> jackson2JsonRedisSerializer() {
		//使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
		Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper mapper = new ObjectMapper();
		//mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		serializer.setObjectMapper(mapper);
		return serializer;
	}*/

	/*@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		//RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
		RedisCacheKeyPrefix redisCacheKeyPrefix = new RedisCacheKeyPrefix();
		//RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1)).computePrefixWith(redisCacheKeyPrefix).disableCachingNullValues();
		// redisExpire1h cache配置，过期时间指定是1小时，缓存key的前缀指定成prefixaaa_（存到redis的key会自动添加这个前缀）
		Map<String, RedisCacheConfiguration> paramCacheConfigurationMap = new HashMap<>();
		RedisCacheManager cacheManager = new RedisCacheTtlManager(redisCacheWriter, defaultCacheConfig, paramCacheConfigurationMap);
		return cacheManager;
	}*/
}
