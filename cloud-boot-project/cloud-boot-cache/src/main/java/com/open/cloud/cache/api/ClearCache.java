package com.open.cloud.cache.api;

import com.open.cloud.domain.api.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/10/30 19:31
 */
@RestController
public class ClearCache {

    @Autowired
    private Cache cache;

    @DeleteMapping("/api/cache/evict")
    public BaseResponse evict(@RequestBody CacheBaseRequest cacheBaseRequest) {
        if ("*".equalsIgnoreCase(cacheBaseRequest.getKey())) {
            cache.clear();
        } else {
            cache.evict(cacheBaseRequest.getKey());
        }
        return BaseResponse.success();
    }

    @PostMapping("/api/cache/get")
    public BaseResponse get(@RequestBody CacheBaseRequest cacheBaseRequest) {
        return BaseResponse.success(cache.get(cacheBaseRequest.getKey()));
    }
}
