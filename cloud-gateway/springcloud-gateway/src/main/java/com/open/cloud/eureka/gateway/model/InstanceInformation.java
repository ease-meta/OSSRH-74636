package com.open.cloud.eureka.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

public class InstanceInformation extends InstanceInfoDefinition
{
    @JsonProperty("route")
    private List<RouteDefinition> routeDefinitions;
    
    public InstanceInformation(final InstanceInfoDefinition info) {
        super(info);
    }
    
    public List<RouteDefinition> getRouteDefinitions() {
        return this.routeDefinitions;
    }
    
    public InstanceInformation setRouteDefinitions(final List<RouteDefinition> routeDefinitions) {
        this.routeDefinitions = routeDefinitions;
        return this;
    }
    
    public InstanceInformation() {
    }
}
