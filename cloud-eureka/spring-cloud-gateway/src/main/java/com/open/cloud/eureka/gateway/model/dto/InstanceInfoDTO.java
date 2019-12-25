package com.open.cloud.eureka.gateway.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.open.cloud.eureka.gateway.model.GatewayRouteDefinition;
import com.open.cloud.eureka.gateway.model.InstanceInfoDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class InstanceInfoDTO extends InstanceInfoDefinition {
	private static final Logger log;
	@JsonProperty("route")
	private List<GatewayRouteDefinition> routeDefinitions;
	@JsonProperty("limiter")
	private BaseAndTempLimiterDTO limiter;

	public InstanceInfoDTO(final InstanceInfoDefinition info) {
		super(info);
	}

	public List<GatewayRouteDefinition> getRouteDefinitions() {
		return this.routeDefinitions;
	}

	public BaseAndTempLimiterDTO getLimiter() {
		return this.limiter;
	}

	public InstanceInfoDTO setRouteDefinitions(final List<GatewayRouteDefinition> routeDefinitions) {
		this.routeDefinitions = routeDefinitions;
		return this;
	}

	public InstanceInfoDTO setLimiter(final BaseAndTempLimiterDTO limiter) {
		this.limiter = limiter;
		return this;
	}

	public InstanceInfoDTO() {
	}

	@Override
	public String toString() {
		return "InstanceInfoDTO(routeDefinitions=" + this.getRouteDefinitions() + ", limiter=" + this.getLimiter() + ")";
	}

	static {
		log = LoggerFactory.getLogger((Class) InstanceInfoDTO.class);
	}
}
