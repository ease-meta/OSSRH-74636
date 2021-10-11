package com.open.cloud.cache;

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
        return delimiter != null ? cacheName.concat(delimiter) : cacheName.concat(CacheConstant.COLON);
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
