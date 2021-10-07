package com.open.cloud.cache;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.embedded.CaffeineCacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/29 23:29
 */
public class Test {
    public static void main(String[] args) {
        /*Cache cache = LinkedHashMapCacheBuilder.createLinkedHashMapCacheBuilder()
                .limit(100)
                .expireAfterWrite(200, TimeUnit.SECONDS)
                .buildCache();*/

        Cache cache = CaffeineCacheBuilder.createCaffeineCacheBuilder()
                .limit(100)
                .expireAfterWrite(200, TimeUnit.SECONDS)
                .buildCache();

    }

}
