package com.open.cloud.eureka.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.open.cloud.eureka.gateway.util.PatternUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

public class GatewayInformation
{
    private static final Logger log;
    @JsonProperty("service_name")
    private String serviceName;
    @JsonProperty("route")
    private List<RouteDefinition> routeDefinitions;
    @JsonProperty("instance")
    private List<InstanceInformation> instances;
    
    public GatewayInformation revertInstance(final List<ServiceInstance> serviceInstances, final List<RouteDefinition> routeDefinitionList) {
        for (final ServiceInstance temp : serviceInstances) {
            final InstanceInformation test = new InstanceInformation((InstanceInfoDefinition)temp);
            this.instances.add(test);
        }
        for (final InstanceInformation information : this.instances) {
            Optional.ofNullable(information.getRouteDefinitions()).orElse(information.setRouteDefinitions(new ArrayList<RouteDefinition>()).getRouteDefinitions()).addAll(mergeInstanceRoute(information.getUri().toString(), routeDefinitionList));
        }
        return this;
    }
    
    public static List<RouteDefinition> mergeInstanceRoute(final String host, final List<RouteDefinition> routeDefinitionList) {
        final List<RouteDefinition> list = new ArrayList<RouteDefinition>();
        for (final RouteDefinition routeDefinition : routeDefinitionList) {
            final Matcher matcher = PatternUtil.PATTERN_INSTANCE.matcher(routeDefinition.getUri().toString());
            while (matcher.find()) {
                if (host.equals(matcher.group())) {
                    list.add(routeDefinition);
                }
            }
        }
        return list;
    }
    
    public static List<RouteDefinition> mergeServiceRoute(final String serviceName, final List<RouteDefinition> routeDefinitionList) {
        final List<RouteDefinition> list = new ArrayList<RouteDefinition>();
        for (final RouteDefinition routeDefinition : routeDefinitionList) {
            final Matcher matcher = PatternUtil.PATTERN_SERVICE.matcher(routeDefinition.getUri().toString());
            while (matcher.find()) {
                if (serviceName.equals(matcher.group())) {
                    list.add(routeDefinition);
                }
            }
        }
        return list;
    }
    
    public GatewayInformation setRouteDefinitions(final String serviceName, final List<RouteDefinition> routeDefinitionList) {
        this.setRouteDefinitions(mergeServiceRoute(serviceName, routeDefinitionList));
        return this;
    }
    
    public String getServiceName() {
        return this.serviceName;
    }
    
    public List<RouteDefinition> getRouteDefinitions() {
        return this.routeDefinitions;
    }
    
    public List<InstanceInformation> getInstances() {
        return this.instances;
    }
    
    public GatewayInformation setServiceName(final String serviceName) {
        this.serviceName = serviceName;
        return this;
    }
    
    public GatewayInformation setRouteDefinitions(final List<RouteDefinition> routeDefinitions) {
        this.routeDefinitions = routeDefinitions;
        return this;
    }
    
    public GatewayInformation setInstances(final List<InstanceInformation> instances) {
        this.instances = instances;
        return this;
    }
    
    public GatewayInformation() {
        this.instances = new ArrayList<InstanceInformation>();
    }
    
    @Override
    public String toString() {
        return "GatewayInformation(serviceName=" + this.getServiceName() + ", routeDefinitions=" + this.getRouteDefinitions() + ", instances=" + this.getInstances() + ")";
    }
    
    static {
        log = LoggerFactory.getLogger((Class)GatewayInformation.class);
    }
}
