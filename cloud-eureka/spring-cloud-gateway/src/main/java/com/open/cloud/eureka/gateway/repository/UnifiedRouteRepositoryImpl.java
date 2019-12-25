package com.open.cloud.eureka.gateway.repository;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Component
public class UnifiedRouteRepositoryImpl
		implements RouteDefinitionRepository {
	private static final Logger log = LoggerFactory.getLogger(UnifiedRouteRepositoryImpl.class);


	public static final String GATEWAY_ROUTES = "gateway_routes";


	@Autowired
	private StringRedisTemplate redisTemplate;


	private final Map<String, RouteDefinition> routeDefinitionMap = Collections.synchronizedMap(new LinkedHashMap<>());

	@PostConstruct
	public void routeInit() {
		Set<String> keys = new HashSet<>();
		keys.addAll(this.routeDefinitionMap.keySet());
		this.redisTemplate.opsForHash().values("gateway_routes").stream().forEach(routeDefinition -> {
			RouteDefinition tempRoute = (RouteDefinition) JSON.parseObject(routeDefinition.toString(), RouteDefinition.class);
			if (this.routeDefinitionMap.containsKey(tempRoute.getId())) {
				this.routeDefinitionMap.replace(tempRoute.getId(), tempRoute);
			} else {
				this.routeDefinitionMap.put(tempRoute.getId(), tempRoute);
			}
			keys.remove(tempRoute.getId());
		});

		for (String key : keys) {
			this.routeDefinitionMap.remove(key);
		}
	}


	public Flux<RouteDefinition> getRouteDefinitions() {
		try {
			this.redisTemplate.hasKey("gateway_routes");
			routeInit();
			return Flux.fromIterable(this.routeDefinitionMap.values());
		} catch (QueryTimeoutException e) {

			return Flux.fromIterable(this.routeDefinitionMap.values());
		}
	}


	public Mono<Void> save(Mono<RouteDefinition> route) {
		return route
				.flatMap(routeDefinition -> {
					this.redisTemplate.opsForHash().put("gateway_routes", routeDefinition.getId(),
							JSON.toJSONString(routeDefinition));
					this.routeDefinitionMap.put(routeDefinition.getId(), routeDefinition);
					return Mono.empty();
				});
	}


	public Mono<Void> delete(Mono<String> routeId) {
		return routeId.flatMap(id -> {
			if (this.redisTemplate.opsForHash().hasKey("gateway_routes", id).booleanValue()) {
				this.redisTemplate.opsForHash().delete("gateway_routes", new Object[]{id});
				this.routeDefinitionMap.remove(id);
				return Mono.empty();
			}
			//return Mono.defer();
			return Mono.empty();
		});
	}
}