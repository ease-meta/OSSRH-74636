package com.open.cloud.webflux.filter;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 *
 * @author Leijian
 * @date   2020/6/22 17:09
 */
public class WebfluxFilter implements WebFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
		return webFilterChain.filter(serverWebExchange);
	}


}
