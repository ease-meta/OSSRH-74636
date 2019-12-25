package com.open.cloud.eureka.gateway.resolver;

import org.springframework.cloud.gateway.filter.ratelimit.*;
import org.springframework.web.server.*;
import reactor.core.publisher.*;

public class QueryParamKeyResolver implements KeyResolver {
	public static final String BEAN_NAME = "queryParamKeyResolver";

	public Mono<String> resolve(final ServerWebExchange exchange) {
		return (Mono<String>) Mono.just(("queryParamKeyResolver@" + (String) exchange.getRequest().getQueryParams().getFirst("userId")));
	}
}
