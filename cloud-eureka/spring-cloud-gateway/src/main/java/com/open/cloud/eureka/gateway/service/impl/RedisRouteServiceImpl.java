package com.open.cloud.eureka.gateway.service.impl;


import com.alibaba.fastjson.JSON;
import com.open.cloud.eureka.gateway.exception.CommonException;
import com.open.cloud.eureka.gateway.model.BasicRouteDefinition;
import com.open.cloud.eureka.gateway.model.GatewayFilterDefinition;
import com.open.cloud.eureka.gateway.model.GatewayInformation;
import com.open.cloud.eureka.gateway.model.GatewayPredicateDefinition;
import com.open.cloud.eureka.gateway.model.GatewayRouteDefinition;
import com.open.cloud.eureka.gateway.model.InstanceInfoDefinition;
import com.open.cloud.eureka.gateway.model.dto.ServiceIDDTO;
import com.open.cloud.eureka.gateway.route.DynamicRouteServiceImpl;
import com.open.cloud.eureka.gateway.service.InstanceService;
import com.open.cloud.eureka.gateway.service.RouteService;
import com.open.cloud.eureka.gateway.util.PatternUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service("RedisRouteService")
public class RedisRouteServiceImpl
		implements RouteService {
	private static final Logger log = LoggerFactory.getLogger(RedisRouteServiceImpl.class);


	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private RouteDefinitionRepository unifiedRouteRepositoryImpl;

	@Autowired
	private InstanceService instanceService;

	@Autowired
	DynamicRouteServiceImpl dynamicRouteService;


	public List<RouteDefinition> getRoutes() {
		List<RouteDefinition> routeDefinitions = new ArrayList<>();
		this.unifiedRouteRepositoryImpl.getRouteDefinitions().subscribe(routeDefinitions::add);
		return routeDefinitions;
	}


	public List<RouteDefinition> getRoutes(String serviceName) {
		List<RouteDefinition> routeDefinitions = new ArrayList<>();
		this.unifiedRouteRepositoryImpl.getRouteDefinitions().subscribe(routeDefinitions::add);
		return GatewayInformation.mergeServiceRoute(serviceName, routeDefinitions);
	}


	public List<RouteDefinition> getRoutes(String serviceName, String instanceID) {
		List<RouteDefinition> routeDefinitions = new ArrayList<>();
		this.unifiedRouteRepositoryImpl.getRouteDefinitions().subscribe(routeDefinitions::add);
		return GatewayInformation.mergeInstanceRoute((
				(ServiceInstance) ((HashMap) Optional.ofNullable(this.instanceService.getInstances(serviceName))
						.orElse(this.instanceService.getInstances(serviceName.toLowerCase())))
						.get(instanceID)).getUri().toString(), routeDefinitions);
	}


	public List<RouteDefinition> getRoutesWithoutKey(String key) {
		Pattern pattern = Pattern.compile("^((?!" + key + ").)*$");
		List<RouteDefinition> list = new ArrayList<>();
		this.unifiedRouteRepositoryImpl.getRouteDefinitions().subscribe(routeDefinition -> {
			Matcher matcher = pattern.matcher(routeDefinition.getId());
			while (matcher.find()) {
				list.add(routeDefinition);
			}
		});

		return list;
	}


	public List<RouteDefinition> getRoutesByKey(String key) {
		Pattern pattern = Pattern.compile(".*(?=" + key + ")");
		List<RouteDefinition> list = new ArrayList<>();
		this.unifiedRouteRepositoryImpl.getRouteDefinitions().subscribe(routeDefinition -> {
			Matcher matcher = pattern.matcher(routeDefinition.getId());
			while (matcher.find()) {
				list.add(routeDefinition);
			}
		});

		return list;
	}


	public Map<String, RouteDefinition> getRoutesMap() {
		Map<String, RouteDefinition> map = new LinkedHashMap<>();
		this.unifiedRouteRepositoryImpl.getRouteDefinitions().subscribe(routeDefinition ->
				map.put(routeDefinition.getId(), routeDefinition));

		return map;
	}


	public Map<String, RouteDefinition> getRoutesMapWithoutKey(String key) {
		Pattern pattern = Pattern.compile("^((?!" + key + ").)*$");
		Map<String, RouteDefinition> map = new LinkedHashMap<>();
		this.unifiedRouteRepositoryImpl.getRouteDefinitions().subscribe(routeDefinition -> {
			Matcher matcher = pattern.matcher(routeDefinition.getId());
			while (matcher.find()) {
				map.put(routeDefinition.getId(), routeDefinition);
			}
		});

		return map;
	}


	public Map<String, RouteDefinition> getRoutesMapByKey(String key) {
		Pattern pattern = Pattern.compile(".*(?=" + key + ")");
		Map<String, RouteDefinition> map = new LinkedHashMap<>();
		this.unifiedRouteRepositoryImpl.getRouteDefinitions().subscribe(routeDefinition -> {
			Matcher matcher = pattern.matcher(routeDefinition.getId());
			while (matcher.find()) {
				map.put(routeDefinition.getId(), routeDefinition);
			}
		});

		return map;
	}


	public GatewayRouteDefinition revertRoutes(BasicRouteDefinition route) {
		List<GatewayPredicateDefinition> predicates = new ArrayList<>();
		List<GatewayFilterDefinition> filters = new ArrayList<>();
		if (route.getPath() != null) {
			Map<String, String> args = new LinkedHashMap<>();
			String path = route.getPath();
			if (!path.startsWith("/")) {
				route.setPath("/" + path);
			}
			args.put("pattern", route.getPath() + "/**");
			predicates.add(revertPredicate("Path", args));
		}

		if (route.getHeader() != null) {
			Map<String, String> map = route.getHeader();

			for (String key : map.keySet()) {
				Map<String, String> args = new LinkedHashMap<>();
				args.put("header", key);
				args.put("regexp", map.get(key));
				predicates.add(revertPredicate("Header", args));
			}
		}
		if (route.getCookie() != null) {
			Map<String, String> map = route.getCookie();

			for (String key : map.keySet()) {
				Map<String, String> args = new LinkedHashMap<>();
				args.put("name", key);
				args.put("regexp", map.get(key));
				predicates.add(revertPredicate("Cookie", args));
			}
		}
		if (route.getMethod() != null) {
			Map<String, String> args = new LinkedHashMap<>();
			args.put("method", route.getMethod());
			predicates.add(revertPredicate("Method", args));
		}
		if (route.getPath() != null) {
			Map<String, String> args = new LinkedHashMap<>();


			String reUri = route.getUri();


			Matcher matcher = PatternUtil.REMOVE_SCHEMA.matcher(reUri);
			if (matcher.find()) {
				matcher = PatternUtil.GET_URL_PATH.matcher(matcher.group());

				if (matcher.find()) {
					args.put("replacement", "/" + matcher.group() + "${remaining}");
				} else {
					args.put("replacement", "${remaining}");
				}
			} else {
				throw (new CommonException()).setCode("1005");
			}

			if (((Boolean) Optional.ofNullable(route.getStripPrefix()).orElse(Boolean.valueOf(true))).booleanValue()) {
				args.put("regexp", route.getPath() + "(?<remaining>.*)");
			} else {
				args.put("regexp", "(?<remaining>.*)");
			}

			filters.add(revertFilter("RewritePath", args));
		} else {
			throw (new CommonException()).setCode("1005");
		}
		if (((Boolean) Optional.ofNullable(route.getHystrixFlag()).orElse(Boolean.valueOf(false))).booleanValue()) {
			Map<String, String> args = new LinkedHashMap<>();
			args.put("name", Optional.ofNullable(route.getHystrixName()).orElse("fallbackcmd"));
			String fallBack = Optional.ofNullable(route.getFallbackUri()).orElse("forward:/fallback");
			if (!fallBack.startsWith("forward:/")) {
				args.put("fallbackUri", "forward:/" + fallBack);
			} else {
				args.put("fallbackUri", fallBack);
			}
			filters.add(revertFilter("Hystrix", args));
		}

		GatewayRouteDefinition routeDefinition = new GatewayRouteDefinition();
		routeDefinition.setId(route.getId());
		routeDefinition.setUri(route.getUri());
		routeDefinition.setPredicates(predicates);
		routeDefinition.setFilters(filters);
		return routeDefinition;
	}


	public String revertID(String id) {
		String modId = id;
		if (!this.dynamicRouteService.exist(id).booleanValue()) {
			modId = id + "@" + (int) (Math.random() * 1000000.0D);
		}
		return modId;
	}


	public GatewayPredicateDefinition revertPredicate(String name, Map<String, String> args) {
		GatewayPredicateDefinition predicateDefinition = new GatewayPredicateDefinition();
		predicateDefinition.setName(name);
		predicateDefinition.setArgs(args);
		return predicateDefinition;
	}


	public GatewayFilterDefinition revertFilter(String name, Map<String, String> args) {
		GatewayFilterDefinition filterDefinition = new GatewayFilterDefinition();
		filterDefinition.setName(name);
		filterDefinition.setArgs(args);
		return filterDefinition;
	}


	public RouteDefinition assembleRouteDefinition(GatewayRouteDefinition gwdefinition) {
		RouteDefinition definition = new RouteDefinition();
		List<PredicateDefinition> pdList = new ArrayList<>();
		List<FilterDefinition> filterList = new ArrayList<>();
		definition.setId(gwdefinition.getId());
		List<GatewayPredicateDefinition> gatewayPredicateDefinitionList = gwdefinition.getPredicates();
		for (GatewayPredicateDefinition gpDefinition : gatewayPredicateDefinitionList) {
			PredicateDefinition predicate = new PredicateDefinition();
			predicate.setArgs(gpDefinition.getArgs());
			predicate.setName(gpDefinition.getName());
			pdList.add(predicate);
		}
		definition.setPredicates(pdList);
		List<GatewayFilterDefinition> gatewayFilterDefinitionList = gwdefinition.getFilters();
		HashMap<String, GatewayFilterDefinition> limiters = new HashMap<>();
		for (GatewayFilterDefinition gfDefinition : gatewayFilterDefinitionList) {
			FilterDefinition filter = new FilterDefinition();

			if ("RequestRateLimiter".equals(gfDefinition.getName())) {
				String limiterId = (String) gfDefinition.getArgs().get("key-resolver");
				limiters.put(limiterId, gfDefinition);
			}
			filter.setName(gfDefinition.getName());
			filter.setArgs(gfDefinition.getArgs());
			filterList.add(filter);
		}
		this.redisTemplate.opsForHash().put("rate_config", gwdefinition.getId(), JSON.toJSONString(limiters));
		definition.setFilters(filterList);
		URI uri = UriComponentsBuilder.fromUriString(gwdefinition.getUri()).build().toUri();
		definition.setUri(uri);
		definition.setOrder(gwdefinition.getOrder());
		return definition;
	}


	public Boolean saveFilterDefinition(RouteDefinition route) {
		HashMap<String, FilterDefinition> limiters = new HashMap<>();
		List<FilterDefinition> list = route.getFilters();

		for (FilterDefinition filterDefinition : list) {
			if ("RequestRateLimiter".equals(filterDefinition.getName())) {
				String limiterId = (String) filterDefinition.getArgs().get("key-resolver");
				limiters.put(limiterId, filterDefinition);
			}
		}
		this.redisTemplate.opsForHash().put("rate_config", route.getId(), JSON.toJSONString(limiters));
		return Boolean.valueOf(true);
	}


	public ServiceIDDTO getServiceName(String routeUri) {
		try {
			Matcher matcher = PatternUtil.PATTERN_SERVICE.matcher(routeUri);
			if (matcher.find()) {
				String serviceName = matcher.group();
				HashMap serviceInstance = this.instanceService.getInstances(serviceName);
				if ((((serviceInstance == null) ? 1 : 0) | ((serviceInstance.size() == 0) ? 1 : 0)) != 0) {
					throw (new CommonException()).setCode("1012");
				}
				return (new ServiceIDDTO()).setServiceID(serviceName);
			}

			matcher = PatternUtil.PATTERN_INSTANCE.matcher(routeUri);
			if (matcher.find()) {

				InstanceInfoDefinition instance = (InstanceInfoDefinition) this.instanceService.getInstance(matcher.group());
				return (new ServiceIDDTO())
						.setServiceID(instance.getServiceId())
						.setInstanceID(instance.getInstanceId());
			}
		} catch (NullPointerException e) {
			log.error("{},{}", e, e.getMessage());
			throw (new CommonException()).setCode("1012");
		}
		return null;
	}


	public ServiceInstance getInstanceByID(String intanceID) {
		return null;
	}


	public GatewayRouteDefinition getRouteById(String routeID) {
		return this.dynamicRouteService.get(routeID);
	}
}
