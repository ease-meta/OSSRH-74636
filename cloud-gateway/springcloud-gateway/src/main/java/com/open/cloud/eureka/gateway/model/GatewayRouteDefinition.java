package com.open.cloud.eureka.gateway.model;

import java.util.*;

public class GatewayRouteDefinition
{
    private String id;
    private List<GatewayPredicateDefinition> predicates;
    private List<GatewayFilterDefinition> filters;
    private List<ParamListInformation> predicateParam;
    private List<ParamListInformation> filterParam;
    private String uri;
    private int order;
    
    public GatewayRouteDefinition() {
        this.predicates = new ArrayList<GatewayPredicateDefinition>();
        this.filters = new ArrayList<GatewayFilterDefinition>();
        this.predicateParam = new ArrayList<ParamListInformation>();
        this.filterParam = new ArrayList<ParamListInformation>();
        this.order = 0;
    }
    
    public List<GatewayPredicateDefinition> getPredicates() {
        return this.predicates;
    }
    
    public GatewayRouteDefinition setPredicates(final List<GatewayPredicateDefinition> predicates) {
        this.predicates = predicates;
        return this;
    }
    
    public List<GatewayFilterDefinition> getFilters() {
        return this.filters;
    }
    
    public GatewayRouteDefinition setFilters(final List<GatewayFilterDefinition> filters) {
        this.filters = filters;
        return this;
    }
    
    public String getUri() {
        return this.uri;
    }
    
    public GatewayRouteDefinition setUri(final String uri) {
        this.uri = uri;
        return this;
    }
    
    public int getOrder() {
        return this.order;
    }
    
    public GatewayRouteDefinition setOrder(final int order) {
        this.order = order;
        return this;
    }
    
    public String getId() {
        return this.id;
    }
    
    public GatewayRouteDefinition setId(final String id) {
        this.id = id;
        return this;
    }
    
    public List<ParamListInformation> getFilterParam() {
        return this.filterParam;
    }
    
    public GatewayRouteDefinition setFilterParam(final List<ParamListInformation> filterParam) {
        this.filterParam = filterParam;
        return this;
    }
    
    public List<ParamListInformation> getPredicateParam() {
        return this.predicateParam;
    }
    
    public GatewayRouteDefinition setPredicateParam(final List<ParamListInformation> predicateParam) {
        this.predicateParam = predicateParam;
        return this;
    }
}
