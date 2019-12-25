package com.open.cloud.eureka.gateway.util;

import com.open.cloud.eureka.gateway.controller.ParamController;
import com.open.cloud.eureka.gateway.model.ParamListInformation;
import com.open.cloud.eureka.gateway.model.dto.BaseAndTempLimiterDTO;
import com.open.cloud.eureka.gateway.service.CircuitBreakerService;
import com.open.cloud.eureka.gateway.service.LimiterService;
import com.open.cloud.eureka.gateway.task.BasicInstanceCheckTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ServiceHandler {
	@Autowired
	@Qualifier("EurekaInstanceCheck")
	BasicInstanceCheckTask instanceCheckTask;
	@Autowired
	ParamController paramController;
	@Autowired
	LimiterService limiterService;
	@Autowired
	CircuitBreakerService circuitBreakerService;
	static ServiceHandler serviceHandler;

	@PostConstruct
	public void init() {
		ServiceHandler.serviceHandler = this;
	}

	public static void initTask() {
		ServiceHandler.serviceHandler.instanceCheckTask.init();
		ServiceHandler.serviceHandler.circuitBreakerService.initCircuitBreakers();
	}

	public static ParamListInformation getFilterByName(final FilterDefinition filter) {
		return ServiceHandler.serviceHandler.paramController.getFilterByName(filter);
	}

	public static ParamListInformation getPredicateByName(final PredicateDefinition predicate) {
		return ServiceHandler.serviceHandler.paramController.getPredicateByName(predicate);
	}

	public static BaseAndTempLimiterDTO getLimiter(final String key) {
		return ServiceHandler.serviceHandler.limiterService.getLimiters(key);
	}
}
