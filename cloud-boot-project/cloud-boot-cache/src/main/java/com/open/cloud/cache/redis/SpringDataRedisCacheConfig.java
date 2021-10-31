package com.open.cloud.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
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
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            String packageName = target.getClass().getPackage().getName();
            if (StringUtils.hasText(packageName)) {
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
    public CacheResolver cacheResolver() {
        return RedisCacheResolver.of(this.cacheManager);
    }
}
