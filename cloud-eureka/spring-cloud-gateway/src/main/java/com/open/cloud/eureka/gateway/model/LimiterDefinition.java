package com.open.cloud.eureka.gateway.model;

import org.springframework.util.*;
import java.util.*;

public class LimiterDefinition
{
    private String id;
    private GatewayPredicateDefinition predicate;
    private GatewayFilterDefinition filter;
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final LimiterDefinition limiter = (LimiterDefinition)o;
        return (!StringUtils.isEmpty((Object)limiter.getId()) && !StringUtils.isEmpty((Object)this.id) && limiter.getId().equals(this.id)) || (this.filter != null && limiter.getFilter() != null && Objects.equals(this.filter.getArgs().get("key-resolver"), limiter.getFilter().getArgs().get("key-resolver")) && Objects.equals(this.filter.getArgs().get("custom-redis-rate-limiter.key"), limiter.getFilter().getArgs().get("custom-redis-rate-limiter.key")) && Objects.equals(this.filter.getArgs().get("custom-redis-rate-limiter.value"), limiter.getFilter().getArgs().get("custom-redis-rate-limiter.value")));
    }
    
    public String getId() {
        return this.id;
    }
    
    public GatewayPredicateDefinition getPredicate() {
        return this.predicate;
    }
    
    public GatewayFilterDefinition getFilter() {
        return this.filter;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public void setPredicate(final GatewayPredicateDefinition predicate) {
        this.predicate = predicate;
    }
    
    public void setFilter(final GatewayFilterDefinition filter) {
        this.filter = filter;
    }
    
    @Override
    public String toString() {
        return "LimiterDefinition(id=" + this.getId() + ", predicate=" + this.getPredicate() + ", filter=" + this.getFilter() + ")";
    }
}
