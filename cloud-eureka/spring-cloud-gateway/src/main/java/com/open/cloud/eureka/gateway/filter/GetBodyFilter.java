package com.open.cloud.eureka.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class GetBodyFilter implements GlobalFilter, Ordered {
	private static final Logger log;
	@Value("#{'${print.headers:}'.split(',')}")
	private Set<String> headers;
	private static final String CACHE_REQUEST_BODY_OBJECT_KEY = "bodyCache";
	private static final String START_TIME = "startTime";
	private static final List<HttpMessageReader<?>> MESSAGE_READERS;

	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		String requestUri = request.getPath().pathWithinApplication().value();
		MultiValueMap<String, String> queryParams = request.getQueryParams();
		Map<String, String> headerMap = new LinkedHashMap<>();
		Object obj = exchange.getAttributes().get(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
		Route route = (Route) obj;
		log.info(route.getId());

		for (String h : this.headers) {
			if ("*".equalsIgnoreCase(h)) {
				headerMap = request.getHeaders().toSingleValueMap();
				break;
			}
			String header = request.getHeaders().getFirst(h);
			headerMap.put(h, header);
		}


		StopWatch stopWatch = new StopWatch(requestUri);
		stopWatch.start();
		exchange.getAttributes().put("startTime", stopWatch);

		log.info("URL: {} Params {} ,Header: {}", new Object[]{requestUri, queryParams, headerMap});


		Object bodyFlux = request.getBody();
		/*if (request.getMethod() == HttpMethod.POST) {
			return readBody(exchange).flatMap(exchange1 ->
					chain.filter(exchange1).then(stop(exchange)));
		}*/

		return chain.filter(exchange).then(stop(exchange));
	}

	public int getOrder() {
		return Integer.MIN_VALUE;
	}

	private Mono<Void> stop(ServerWebExchange exchange) {
		return Mono.fromRunnable(() -> {
			StopWatch stop = (StopWatch) exchange.getAttribute("startTime");
			if (stop != null) {
				stop.stop();
				log.info("URL: {}   Cost:{}ms", stop.getId(), Long.valueOf(stop.getTotalTimeMillis()));
			}
		});
	}

	/*private Mono<ServerWebExchange> readBody(ServerWebExchange exchange) {
		Object cachedBody = exchange.getAttribute("bodyCache");
		if (cachedBody != null) {
			return Mono.just(exchange);
		}
		return DataBufferUtils.join((Publisher) exchange.getRequest().getBody())
				.flatMap(dataBuffer -> {
					DataBufferUtils.retain(dataBuffer);
					final Flux<DataBuffer> cachedFlux = Flux.defer();
					ServerHttpRequestDecorator serverHttpRequestDecorator = new ServerHttpRequestDecorator(exchange.getRequest()) {
						public Flux<DataBuffer> getBody() {
							return cachedFlux;
						}
					};
					ServerWebExchange build = exchange.mutate().request((ServerHttpRequest) serverHttpRequestDecorator).build();
					return Mono.just(build)
							.map(())
							.doOnNext(());

				}).defaultIfEmpty(exchange).flatMap(webExchange -> Mono.just(exchange));
	}
*/
	static {
		log = LoggerFactory.getLogger((Class) GetBodyFilter.class);
		MESSAGE_READERS = HandlerStrategies.withDefaults().messageReaders();
	}
}
