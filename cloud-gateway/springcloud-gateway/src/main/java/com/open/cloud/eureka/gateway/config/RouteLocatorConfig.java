package com.open.cloud.eureka.gateway.config;


import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class RouteLocatorConfig {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("path_route", r -> r.path("/meteor_93")
						.uri("https://blog.csdn.net"))
				.build();
	}

	@Bean
	KeyResolver userKeyResolver() {
		return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
	}
}
