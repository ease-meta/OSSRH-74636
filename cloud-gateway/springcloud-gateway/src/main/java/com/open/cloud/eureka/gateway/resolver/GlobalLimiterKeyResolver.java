package com.open.cloud.eureka.gateway.resolver;

import com.open.cloud.eureka.gateway.exception.CommonException;
import com.open.cloud.eureka.gateway.model.ParamInformation;
import com.open.cloud.eureka.gateway.util.LimiterFieldCheckUtil;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

public class GlobalLimiterKeyResolver implements KeyResolver, FieldCheck {
	public static final String BEAN_NAME = "globalLimiterKeyResolver";

	public Mono<String> resolve(final ServerWebExchange exchange) {
		return (Mono<String>) Mono.just("globalLimiterKeyResolver@global");
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
	}
}