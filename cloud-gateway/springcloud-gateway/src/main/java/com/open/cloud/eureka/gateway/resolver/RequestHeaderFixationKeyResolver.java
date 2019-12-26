package com.open.cloud.eureka.gateway.resolver;

import com.alibaba.fastjson.JSON;
import com.open.cloud.eureka.gateway.exception.CommonException;
import com.open.cloud.eureka.gateway.model.ParamInformation;
import com.open.cloud.eureka.gateway.util.LimiterFieldCheckUtil;
import com.open.cloud.eureka.gateway.util.RequestBodyUtil;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class RequestHeaderFixationKeyResolver implements KeyResolver, FieldCheck
{
    public static final String BEAN_NAME = "requestHeaderFixationKeyResolver";
    
    public Mono<String> resolve(final ServerWebExchange exchange) {
        return (Mono<String>)Mono.just(("requestHeaderFixationKeyResolver@" + JSON.toJSONString((Object) RequestBodyUtil.getSystemHeader(exchange))));
    }
    
    public void fieldCheck(final List<ParamInformation> params) {
        final HashMap<String, String> paramsMap = new HashMap<String, String>();
        params.forEach(paramInformation -> paramsMap.put(paramInformation.getName(), paramInformation.getValue()));
        if (!LimiterFieldCheckUtil.isInteger(paramsMap.get("custom-redis-rate-limiter.burstCapacity"))) {
            throw new CommonException().setCode("1014").setMsg("Maximum number of visits is incorrect");
        }
        if (!LimiterFieldCheckUtil.isInteger(paramsMap.get("custom-redis-rate-limiter.replenishRate"))) {
            throw new CommonException().setCode("1014").setMsg("The refresh rate is incorrect");
        }
        final String key = paramsMap.get("custom-redis-rate-limiter.key");
        final String value = paramsMap.get("custom-redis-rate-limiter.value");
        if (StringUtils.isEmpty((Object)key) && StringUtils.isEmpty((Object)value)) {
            throw new CommonException().setCode("1014").setMsg("Field cannot be empty");
        }
        final int keyCount = key.split("-").length;
        final int valueCount = value.split("-").length;
        if (!Objects.equals(keyCount, valueCount)) {
            throw new CommonException().setCode("1014").setMsg("Field is incorrect");
        }
    }
}
