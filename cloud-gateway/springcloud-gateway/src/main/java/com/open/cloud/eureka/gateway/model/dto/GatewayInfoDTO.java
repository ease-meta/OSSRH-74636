package com.open.cloud.eureka.gateway.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.open.cloud.eureka.gateway.model.GatewayInformation;
import com.open.cloud.eureka.gateway.model.GatewayRouteDefinition;
import com.open.cloud.eureka.gateway.model.InstanceInformation;
import com.open.cloud.eureka.gateway.model.ParamListInformation;
import com.open.cloud.eureka.gateway.util.ServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GatewayInfoDTO
{
    private static final Logger log;
    @JsonProperty("service_name")
    private String serviceName;
    @JsonProperty("route")
    private List<GatewayRouteDefinition> routeDefinitions;
    @JsonProperty("instance")
    private List<InstanceInfoDTO> instances;
    @JsonProperty("limiter")
    private BaseAndTempLimiterDTO limiter;
    
    public GatewayInfoDTO revertInfo(final GatewayInformation gatewayInformation) {
        for (final InstanceInformation info : gatewayInformation.getInstances()) {
            final List<GatewayRouteDefinition> list = new ArrayList<GatewayRouteDefinition>();
            for (final RouteDefinition tempRoute : info.getRouteDefinitions()) {
                list.add(this.revertRoute(tempRoute));
            }
            this.instances.add(new InstanceInfoDTO(info).setRouteDefinitions(list).setLimiter(ServiceHandler.getLimiter(info.getInstanceId())));
        }
        final List<GatewayRouteDefinition> list2 = new ArrayList<GatewayRouteDefinition>();
        for (final RouteDefinition routeDefinition : gatewayInformation.getRouteDefinitions()) {
            list2.add(this.revertRoute(routeDefinition));
        }
        this.limiter = ServiceHandler.getLimiter(gatewayInformation.getServiceName());
        return this.setServiceName(gatewayInformation.getServiceName()).setRouteDefinitions(list2);
    }
    
    public GatewayRouteDefinition revertRoute(final RouteDefinition routeDefinition) {
        final GatewayRouteDefinition route = new GatewayRouteDefinition();
        route.setId(routeDefinition.getId()).setOrder(routeDefinition.getOrder()).setUri(routeDefinition.getUri().toString());
        final List<ParamListInformation> filterList = new ArrayList<ParamListInformation>();
        final List<ParamListInformation> predicateList = new ArrayList<ParamListInformation>();
        for (final FilterDefinition filter : routeDefinition.getFilters()) {
            filterList.add(ServiceHandler.getFilterByName(filter));
        }
        for (final PredicateDefinition predicate : routeDefinition.getPredicates()) {
            if (predicate.getName().equals("After") | predicate.getName().equals("Before") | predicate.getName().equals("Between")) {
                final Map<String, String> param = (Map<String, String>)predicate.getArgs();
                if (param.containsKey("datetime")) {
                    final Long timestamp = ZonedDateTime.parse(param.get("datetime")).toInstant().toEpochMilli();
                    param.put("datetime", timestamp.toString());
                }
                if (param.containsKey("datetime1")) {
                    final Long timestamp = ZonedDateTime.parse(param.get("datetime1")).toInstant().toEpochMilli();
                    param.put("datetime1", timestamp.toString());
                }
                if (param.containsKey("datetime2")) {
                    final Long timestamp = ZonedDateTime.parse(param.get("datetime2")).toInstant().toEpochMilli();
                    param.put("datetime2", timestamp.toString());
                }
            }
            predicateList.add(ServiceHandler.getPredicateByName(predicate));
        }
        route.setFilterParam(filterList).setPredicateParam(predicateList);
        return route;
    }
    
    public String getServiceName() {
        return this.serviceName;
    }
    
    public List<GatewayRouteDefinition> getRouteDefinitions() {
        return this.routeDefinitions;
    }
    
    public List<InstanceInfoDTO> getInstances() {
        return this.instances;
    }
    
    public BaseAndTempLimiterDTO getLimiter() {
        return this.limiter;
    }
    
    public GatewayInfoDTO setServiceName(final String serviceName) {
        this.serviceName = serviceName;
        return this;
    }
    
    public GatewayInfoDTO setRouteDefinitions(final List<GatewayRouteDefinition> routeDefinitions) {
        this.routeDefinitions = routeDefinitions;
        return this;
    }
    
    public GatewayInfoDTO setInstances(final List<InstanceInfoDTO> instances) {
        this.instances = instances;
        return this;
    }
    
    public GatewayInfoDTO setLimiter(final BaseAndTempLimiterDTO limiter) {
        this.limiter = limiter;
        return this;
    }
    
    public GatewayInfoDTO() {
        this.instances = new ArrayList<InstanceInfoDTO>();
    }
    
    @Override
    public String toString() {
        return "GatewayInfoDTO(serviceName=" + this.getServiceName() + ", routeDefinitions=" + this.getRouteDefinitions() + ", instances=" + this.getInstances() + ", limiter=" + this.getLimiter() + ")";
    }
    
    static {
        log = LoggerFactory.getLogger((Class)GatewayInfoDTO.class);
    }
}
