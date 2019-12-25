package com.open.cloud.eureka.gateway.resolver;

import com.alibaba.fastjson.JSON;
import com.open.cloud.eureka.gateway.model.SystemRequest;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class ChannelKeyResolver implements KeyResolver {
	public static final String BEAN_NAME = "channelKeyResolver";

	public Mono<String> resolve(final ServerWebExchange exchange) {
		final String cache = (String) exchange.getAttribute("bodyCache");
		final SystemRequest systemRequest = (SystemRequest) JSON.parseObject(cache, (Class) SystemRequest.class);
		return (Mono<String>) Mono.just(("channelKeyResolver@" + Objects.requireNonNull(systemRequest).getSysHead().getCnlCd()));
	}
}
