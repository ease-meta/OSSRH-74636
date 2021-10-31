package com.open.cloud.cache.api;

import com.open.cloud.domain.api.BaseRequest;
import lombok.Data;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/10/30 19:46
 */
@Data
public class CacheBaseRequest extends BaseRequest {

    private String key;
}
