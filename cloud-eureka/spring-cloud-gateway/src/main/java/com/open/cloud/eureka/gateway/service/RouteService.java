package com.open.cloud.eureka.gateway.service;

import com.open.cloud.eureka.gateway.model.BasicRouteDefinition;
import com.open.cloud.eureka.gateway.model.GatewayFilterDefinition;
import com.open.cloud.eureka.gateway.model.GatewayPredicateDefinition;
import com.open.cloud.eureka.gateway.model.GatewayRouteDefinition;
import com.open.cloud.eureka.gateway.model.dto.ServiceIDDTO;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;
import java.util.Map;

public interface RouteService {
	List<RouteDefinition> getRoutes();

	List<RouteDefinition> getRoutes(final String serviceName);

	List<RouteDefinition> getRoutes(final String serviceName, final String instanceID);

	GatewayRouteDefinition revertRoutes(final BasicRouteDefinition route);

	String revertID(final String ID);

	GatewayPredicateDefinition revertPredicate(final String name, final Map<String, String> args);

	GatewayFilterDefinition revertFilter(final String name, final Map<String, String> args);

	List<RouteDefinition> getRoutesWithoutKey(final String key);

	List<RouteDefinition> getRoutesByKey(final String key);

	Map<String, RouteDefinition> getRoutesMap();

	Map<String, RouteDefinition> getRoutesMapWithoutKey(final String key);

	Map<String, RouteDefinition> getRoutesMapByKey(final String key);

	RouteDefinition assembleRouteDefinition(final GatewayRouteDefinition gwdefinition);

	Boolean saveFilterDefinition(final RouteDefinition route);

	ServiceIDDTO getServiceName(final String routeUri);

	ServiceInstance getInstanceByID(final String intanceID);

	GatewayRouteDefinition getRouteById(final String routeID);
}
