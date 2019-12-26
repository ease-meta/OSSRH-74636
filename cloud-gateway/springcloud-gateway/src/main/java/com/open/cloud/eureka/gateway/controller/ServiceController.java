package com.open.cloud.eureka.gateway.controller;

import com.google.common.collect.Lists;
import com.netflix.config.ConfigurationManager;
import com.open.cloud.eureka.gateway.common.ResultDO;
import com.open.cloud.eureka.gateway.exception.CommonException;
import com.open.cloud.eureka.gateway.model.GatewayInformation;
import com.open.cloud.eureka.gateway.model.dto.GatewayInfoDTO;
import com.open.cloud.eureka.gateway.resolver.RemoteAddrKeyResolver;
import com.open.cloud.eureka.gateway.route.DynamicRouteServiceImpl;
import com.open.cloud.eureka.gateway.service.InstanceService;
import com.open.cloud.eureka.gateway.service.LimiterService;
import com.open.cloud.eureka.gateway.service.RouteService;
import com.open.cloud.eureka.gateway.util.RedisCacheUtil;
import org.apache.commons.configuration.AbstractConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/gateway/service"})
public class ServiceController {
	@Autowired
	private DynamicRouteServiceImpl dynamicRouteService;
	@Autowired
	private RemoteAddrKeyResolver remoteAddrKeyResolver;
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private RouteDefinitionRepository unifiedRouteRepositoryImpl;
	@Autowired
	private InstanceService instanceService;
	@Autowired
	private RouteService routeService;
	@Autowired
	private LimiterService limiterService;
	@Autowired
	private RedisCacheUtil redisCacheUtil;

	@GetMapping({"/getServices"})
	public ResponseEntity<?> getServices() {
		final List<GatewayInfoDTO> list = new ArrayList<GatewayInfoDTO>();
		final List<RouteDefinition> routeDefinitions = routeService.getRoutesWithoutKey("\\$");
		for (final String serviceName : instanceService.getServices()) {
			final List instances = Lists.newArrayList((Iterable) instanceService.getInstances(serviceName).values());
			list.add(new GatewayInfoDTO().revertInfo(new GatewayInformation().setServiceName(serviceName).revertInstance(instances, routeDefinitions).setRouteDefinitions(serviceName, routeDefinitions)));
		}
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("success").setData(list));
	}

	@GetMapping({"/getServices/{serviceName}"})
	public ResponseEntity<?> getServicesByName(@PathVariable final String serviceName) {
		List<RouteDefinition> routeDefinitions = null;
		final List<GatewayInformation> gatewayInformations = new ArrayList<GatewayInformation>();
		routeDefinitions = routeService.getRoutesWithoutKey("\\$");
		try {
			final List instances = Lists.newArrayList((Iterable) instanceService.getInstances(serviceName).values());
			gatewayInformations.add(new GatewayInformation().setServiceName(serviceName).revertInstance(instances, routeDefinitions).setRouteDefinitions(serviceName, routeDefinitions));
			return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("success").setData(gatewayInformations));
		} catch (NullPointerException e) {
			throw new CommonException().setCode("1009");
		}
	}

	@GetMapping({"/getServices/{serviceName}/{instanceName}"})
	public ResponseEntity<?> getInstances(@PathVariable String serviceName, @PathVariable String instanceName) {
		try {
			List instances = Lists.newArrayList(instanceService.getInstances(serviceName).values());

			/*for (InstanceInfoDefinition instance : instances) {
				if (instance.getInstanceId().equals(instanceName)) {
					return ResponseEntity.ok((new ResultDO())
							.setCode("0000").setMessage("success").setData(instance));
				}
			}*/
			throw (new CommonException()).setCode("1010");
		} catch (NullPointerException e) {
			throw (new CommonException()).setCode("1009");
		}
	}

	@GetMapping({"/test"})
	public ResponseEntity<?> test(@RequestParam final Integer time) {
		final String commandKey = "hello-world";
		final String prefix = "hystrix.command." + commandKey + ".";
		final AbstractConfiguration config = ConfigurationManager.getConfigInstance();
		config.setProperty(prefix + "execution.isolation.thread.timeoutInMilliseconds", (Object) time);
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("success"));
	}

	@GetMapping({"/getRedis"})
	public ResponseEntity<?> getRedisCache(@RequestParam final String key) {
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("success").setData(redisCacheUtil.getRedisCache(key)));
	}

	@PostMapping({"/setRedis"})
	public ResponseEntity<?> setRedisCache(@RequestParam final String keyClass, @RequestParam final String key, @RequestBody final Object object) {
		if (redisCacheUtil.saveRedisCache(keyClass, key, object)) {
			return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("success"));
		}
		throw new CommonException().setCode("1017");
	}
}
