package com.open.cloud.eureka.gateway.repository;

import org.springframework.stereotype.*;
import org.springframework.core.annotation.*;
import org.springframework.cloud.client.*;
import java.util.*;

@Component
@Order
public interface InstanceRepository
{
    HashMap<String, HashMap<String, ServiceInstance>> getServiceInstances();
    
    List<String> getServices();
    
    ServiceInstance getInstance(final String uri);
    
    HashMap<String, ServiceInstance> getInstances(final String serviceId);
    
    boolean deleteService(final String serviceId);
    
    boolean deleteInstance(final String serviceId, final String instanceId);
    
    void saveToRedis(final String serviceId, final HashMap<String, ServiceInstance> instances);
}
