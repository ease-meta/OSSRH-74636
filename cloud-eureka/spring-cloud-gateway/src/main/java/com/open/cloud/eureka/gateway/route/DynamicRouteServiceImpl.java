package com.open.cloud.eureka.gateway.route;

import com.alibaba.fastjson.JSON;
import com.open.cloud.eureka.gateway.model.GatewayPredicateDefinition;
import com.open.cloud.eureka.gateway.model.GatewayRouteDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {
	private static final Logger log;

	@Autowired
	private RouteDefinitionWriter routeDefinitionWriter;

	private ApplicationEventPublisher publisher;
	@Autowired
	private StringRedisTemplate redisTemplate;

	public Boolean add(final RouteDefinition definition) {
		this.routeDefinitionWriter.save(Mono.just(definition)).subscribe();
		this.publisher.publishEvent((ApplicationEvent) new RefreshRoutesEvent((Object) this));
		return true;
	}

	public Boolean update(final RouteDefinition definition) {
		try {
			this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
		} catch (Exception e) {
			return false;
		}
		try {
			this.routeDefinitionWriter.save(Mono.just(definition)).subscribe();
			this.publisher.publishEvent((ApplicationEvent) new RefreshRoutesEvent((Object) this));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Boolean delete(final String id) {
		try {
			this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
			this.redisTemplate.opsForHash().delete("gateway_routes", new Object[]{id});
			this.publisher.publishEvent((ApplicationEvent) new RefreshRoutesEvent((Object) this));
			return true;
		} catch (Exception e) {
			DynamicRouteServiceImpl.log.error("Delete error.{}", (Throwable) e);
			return false;
		}
	}

	public GatewayRouteDefinition get(final String id) {
		return (GatewayRouteDefinition) JSON.parseObject((String) this.redisTemplate.opsForHash().get("gateway_routes", (Object) id), (Class) GatewayRouteDefinition.class);
	}

	public Boolean exist(final String id) {
		return this.redisTemplate.opsForHash().hasKey("gateway_routes", (Object) id);
	}

	public void setApplicationEventPublisher(final ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

	public static void main(final String[] args) {
		final GatewayRouteDefinition definition = new GatewayRouteDefinition();
		final GatewayPredicateDefinition predicate = new GatewayPredicateDefinition();
		final Map<String, String> predicateParams = new HashMap<String, String>(8);
		definition.setId("jd_route");
		predicate.setName("Path");
		predicateParams.put("pattern", "/jd");
		predicate.setArgs(predicateParams);
		definition.setPredicates(Arrays.asList(predicate));
		final String uri = "http://www.jd.com";
		definition.setUri(uri);
		DynamicRouteServiceImpl.log.error("definition:" + JSON.toJSONString((Object) definition));
		final RouteDefinition definition2 = new RouteDefinition();
		final PredicateDefinition predicate2 = new PredicateDefinition();
		final Map<String, String> predicateParams2 = new HashMap<String, String>(8);
		definition2.setId("baidu_route");
		predicate2.setName("Path");
		predicateParams2.put("pattern", "/baidu");
		predicate2.setArgs((Map) predicateParams2);
		definition2.setPredicates((List) Arrays.asList(predicate2));
		final URI uri2 = UriComponentsBuilder.fromHttpUrl("http://www.baidu.com").build().toUri();
		definition2.setUri(uri2);
		DynamicRouteServiceImpl.log.error("definition1：" + JSON.toJSONString((Object) definition2));
	}

	static {
		log = LoggerFactory.getLogger((Class) DynamicRouteServiceImpl.class);
	}
}
