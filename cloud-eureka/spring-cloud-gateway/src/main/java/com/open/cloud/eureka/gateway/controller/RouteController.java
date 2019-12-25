package com.open.cloud.eureka.gateway.controller;

import com.open.cloud.eureka.gateway.common.ResultDO;
import com.open.cloud.eureka.gateway.exception.CommonException;
import com.open.cloud.eureka.gateway.model.BasicRouteDefinition;
import com.open.cloud.eureka.gateway.model.GatewayFilterDefinition;
import com.open.cloud.eureka.gateway.model.GatewayPredicateDefinition;
import com.open.cloud.eureka.gateway.model.GatewayRouteDefinition;
import com.open.cloud.eureka.gateway.model.ParamInformation;
import com.open.cloud.eureka.gateway.model.ParamListInformation;
import com.open.cloud.eureka.gateway.model.dto.ServiceIDDTO;
import com.open.cloud.eureka.gateway.resolver.RemoteAddrKeyResolver;
import com.open.cloud.eureka.gateway.route.DynamicRouteServiceImpl;
import com.open.cloud.eureka.gateway.service.InstanceService;
import com.open.cloud.eureka.gateway.service.LimiterService;
import com.open.cloud.eureka.gateway.service.RouteService;
import com.open.cloud.eureka.gateway.util.FluxPageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping({"/oms/gateway/route"})
public class RouteController {
	private static final Logger log;
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
	@Qualifier("RedisRouteService")
	private RouteService routeService;
	@Autowired
	private LimiterService limiterService;

	@PostMapping({"/add"})
	public ResponseEntity<?> add(@RequestBody final GatewayRouteDefinition gwdefinition) {
		if (this.dynamicRouteService.exist(gwdefinition.getId())) {
			throw new CommonException().setCode("1007");
		}
		final RouteDefinition definition = this.routeService.assembleRouteDefinition(gwdefinition);
		if (!this.dynamicRouteService.add(definition)) {
			throw new CommonException().setCode("1001");
		}
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("Success"));
	}

	@PostMapping({"/addList/custom"})
	public ResponseEntity<?> addCustom(@RequestBody final List<GatewayRouteDefinition> list, @RequestParam(required = false, defaultValue = "false") final Boolean flag) {
		Integer count = 200;
		for (final GatewayRouteDefinition route : list) {
			Boolean uriSkip = false;
			try {
				route.setId(this.routeService.revertID(route.getId()));
				if (!route.getUri().startsWith("lb")) {
					count = 150;
				}
				if (route.getOrder() == 0) {
					route.setOrder(count);
					--count;
				}
				if (this.dynamicRouteService.exist(route.getId())) {
					final GatewayRouteDefinition temp = this.routeService.getRouteById(route.getId());
					if (!temp.getUri().equals(route.getUri())) {
						uriSkip = true;
					}
				}
				final List<GatewayFilterDefinition> filterDefinitions = new ArrayList<GatewayFilterDefinition>();
				final List<GatewayPredicateDefinition> predicateDefinitions = new ArrayList<GatewayPredicateDefinition>();
				for (final ParamListInformation param : route.getPredicateParam()) {
					if (param != null) {
						predicateDefinitions.add(new GatewayPredicateDefinition().setName(param.getName()).setArgs(this.revertParam(param)));
					}
				}
				for (final ParamListInformation param : route.getFilterParam()) {
					if (param != null) {
						filterDefinitions.add(new GatewayFilterDefinition().setName(param.getName()).setArgs(this.revertParam(param)));
					}
				}
				route.setPredicates(predicateDefinitions).setFilters(filterDefinitions);
				if (route.getPredicates() == null || route.getPredicates().isEmpty()) {
					throw new CommonException().setCode("1011");
				}
				if (flag) {
					final RouteDefinition definition = this.routeService.assembleRouteDefinition(route);
					if (!this.dynamicRouteService.add(definition)) {
						throw new CommonException().setCode("1001");
					}
					continue;
				} else {
					if (uriSkip) {
						this.limiterService.deleteLimiterRoute(route.getId());
					}
					final ServiceIDDTO serviceIDDTO = this.routeService.getServiceName(route.getUri());
					if (serviceIDDTO == null) {
						throw new CommonException().setCode("1012");
					}
					final RouteDefinition definition2 = this.routeService.assembleRouteDefinition(route);
					if (!this.dynamicRouteService.add(definition2)) {
						throw new CommonException().setCode("1001");
					}
					if (serviceIDDTO.getInstanceID() == null) {
						this.limiterService.updateLimiterRoute(serviceIDDTO.getServiceID());
					} else {
						this.limiterService.updateLimiterRoute(serviceIDDTO.getServiceID(), serviceIDDTO.getInstanceID());
					}
				}
			} catch (RedisConnectionFailureException e) {
				RouteController.log.error("连接Redis失败");
				e.printStackTrace();
			}
		}
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("Success"));
	}

	@PostMapping({"/addList/classic"})
	public ResponseEntity<?> addClassic(@RequestBody final List<BasicRouteDefinition> list) {
		Integer count = 200;
		for (final BasicRouteDefinition route : list) {
			try {
				route.setId(this.routeService.revertID(route.getId()));
				final GatewayRouteDefinition gatewayRouteDefinition = this.routeService.revertRoutes(route);
				gatewayRouteDefinition.setOrder(Optional.ofNullable(route.getOrder()).orElse(count));
				--count;
				final RouteDefinition definition = this.routeService.assembleRouteDefinition(gatewayRouteDefinition);
				if (!this.dynamicRouteService.add(definition)) {
					throw new CommonException().setCode("1001");
				}
				final ServiceIDDTO serviceIDDTO = this.routeService.getServiceName(route.getUri());
				if (serviceIDDTO.getInstanceID() == null) {
					this.limiterService.updateLimiterRoute(serviceIDDTO.getServiceID());
				} else {
					this.limiterService.updateLimiterRoute(serviceIDDTO.getServiceID(), serviceIDDTO.getInstanceID());
				}
			} catch (RedisConnectionFailureException e) {
				RouteController.log.error("连接Redis失败");
				e.printStackTrace();
			}
		}
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("Success"));
	}

	@DeleteMapping({"/delete/{id}"})
	public ResponseEntity<?> delete(@PathVariable final String id) {
		if (!this.dynamicRouteService.exist(id)) {
			throw new CommonException().setCode("1008");
		}
		if (!this.dynamicRouteService.delete(id)) {
			throw new CommonException().setCode("1003");
		}
		this.limiterService.deleteLimiterRoute(id);
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("Success"));
	}

	@PostMapping({"/update"})
	public ResponseEntity<?> update(@RequestBody final GatewayRouteDefinition gwdefinition) {
		final RouteDefinition definition = this.routeService.assembleRouteDefinition(gwdefinition);
		if (!this.dynamicRouteService.exist(gwdefinition.getId())) {
			throw new CommonException().setCode("1008");
		}
		if (!this.dynamicRouteService.update(definition)) {
			throw new CommonException().setCode("1002");
		}
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("Success"));
	}

	@GetMapping({"/discovery"})
	public ResponseEntity<?> getDiscovery() {
		final List<String> services = (List<String>) this.discoveryClient.getServices();
		final Map<String, List<ServiceInstance>> serviceInstances = new HashMap<String, List<ServiceInstance>>();
		for (final String service : services) {
			serviceInstances.put(service, this.discoveryClient.getInstances(service));
		}
		return (ResponseEntity<?>) ResponseEntity.ok((Object) serviceInstances);
	}

	@GetMapping({"/getRoutes"})
	public ResponseEntity<?> getRoutes(@RequestParam(value = "page", required = false, defaultValue = "1") final Integer page, @RequestParam(value = "per_page", required = false, defaultValue = "10") final Integer perPage) {
		return (ResponseEntity<?>) ResponseEntity.ok((Object) FluxPageUtil.getPageResult((Flux<RouteDefinition>) this.unifiedRouteRepositoryImpl.getRouteDefinitions(), page, perPage));
	}

	@GetMapping({"/getRoutes/{serviceName}"})
	public ResponseEntity<?> getRoutes(@PathVariable final String serviceName) {
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("success").setData(this.routeService.getRoutes(serviceName)));
	}

	@GetMapping({"/getRoutes/{serviceName}/{instanceID}"})
	public ResponseEntity<?> getRoutes(@PathVariable final String serviceName, @PathVariable final String instanceID) {
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("success").setData(this.routeService.getRoutes(serviceName, instanceID)));
	}

	public Map<String, String> revertParam(final ParamListInformation param) {
		final Map<String, String> args = new LinkedHashMap<String, String>();
		if (param.getName().equals("After") | param.getName().equals("Before") | param.getName().equals("Between")) {
			for (final ParamInformation info : param.getArgs()) {
				try {
					final Date date = new Date(new Long(info.getValue()));
					args.put(info.getName(), date.toInstant().atZone(ZoneId.systemDefault()).toString());
				} catch (NullPointerException e) {
					RouteController.log.error("时间为空,无法添加谓词");
					throw new CommonException().setCode("1005");
				}
			}
		} else {
			for (final ParamInformation info : param.getArgs()) {
				args.put(info.getName(), Optional.ofNullable(info.getValue()).orElse("null"));
			}
		}
		return args;
	}

	static {
		log = LoggerFactory.getLogger((Class) RouteController.class);
	}
}
