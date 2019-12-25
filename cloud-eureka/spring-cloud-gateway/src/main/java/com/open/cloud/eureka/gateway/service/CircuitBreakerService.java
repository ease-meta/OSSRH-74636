package com.open.cloud.eureka.gateway.service;

import com.open.cloud.eureka.gateway.model.HystrixDefinition;

import java.util.List;


public interface CircuitBreakerService
{
    Boolean excuteCircuitBreaker(final Object breakerDefinition, final Boolean flag);
    
    List<HystrixDefinition> getCircuitBreakers();
    
    void initCircuitBreakers();
    
    Boolean delCircuitBreaker(final String name);
}
