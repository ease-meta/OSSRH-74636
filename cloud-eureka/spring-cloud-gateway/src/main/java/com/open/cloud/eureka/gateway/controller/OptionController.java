package com.open.cloud.eureka.gateway.controller;

import com.open.cloud.eureka.gateway.common.ResultDO;
import com.open.cloud.eureka.gateway.config.RibbonDefaultConfiguration;
import com.open.cloud.eureka.gateway.exception.CommonException;
import com.open.cloud.eureka.gateway.model.HystrixDefinition;
import com.open.cloud.eureka.gateway.model.RibbonDefinition;
import com.open.cloud.eureka.gateway.service.CircuitBreakerService;
import com.open.cloud.eureka.gateway.service.LoadBalancerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/oms/gateway/option"})
public class OptionController {
	private static final Logger log;
	@Autowired
	private CircuitBreakerService circuitBreakerService;
	@Autowired
	private LoadBalancerService loadBalancerService;
	@Autowired
	private RibbonDefaultConfiguration ribbonDefaultConfiguration;

	@GetMapping({"/getOptions/{key}"})
	public ResponseEntity<?> getRedisCache(@PathVariable final String key) {
		if (key.equals("hystrix")) {
			return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("success").setData(this.circuitBreakerService.getCircuitBreakers()));
		}
		if (key.equals("ribbon")) {
			return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("success").setData(this.loadBalancerService.getLoadBalancersAll()));
		}
		throw new CommonException().setCode("1017");
	}

	@PostMapping({"/setCircuitBreaker"})
	public ResponseEntity<?> setRedisCache(@RequestBody final HystrixDefinition hystrixDefinition) {
		this.circuitBreakerService.excuteCircuitBreaker(hystrixDefinition, false);
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("success"));
	}

	@DeleteMapping({"/delCircuitBreaker/{name}"})
	public ResponseEntity<?> delRedisCache(@PathVariable final String name) {
		this.circuitBreakerService.delCircuitBreaker(name);
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("success"));
	}

	@PostMapping({"/setLoadBalancer"})
	public ResponseEntity<?> setRedisCache(@RequestBody final RibbonDefinition ribbonDefinition) {
		this.loadBalancerService.setLoadBalancer(ribbonDefinition);
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("success"));
	}

	@GetMapping({"/getLoadBalancer/{serviceId}"})
	public ResponseEntity<?> getLoadBalancerById(@PathVariable final String serviceId) {
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("success").setData(this.loadBalancerService.getLoadBalancerByName(serviceId)));
	}

	static {
		log = LoggerFactory.getLogger((Class) OptionController.class);
	}
}
