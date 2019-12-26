package com.open.cloud.eureka.gateway.service;

import java.util.*;

public interface LoadBalancerService
{
    List<Object> getLoadBalancersAll();
    
    Object getLoadBalancerByName(final String name);
    
    Boolean setLoadBalancer(final Object loadBalancerDefiniton);
}
