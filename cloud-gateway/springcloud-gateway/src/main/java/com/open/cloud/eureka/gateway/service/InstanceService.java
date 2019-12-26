package com.open.cloud.eureka.gateway.service;

import org.springframework.beans.factory.*;
import org.springframework.cloud.client.*;
import java.util.*;

public interface InstanceService extends InitializingBean
{
    boolean updateInstanceStatus(final String serviceId, final String instanceId, final String status);
    
    HashMap<String, HashMap<String, ServiceInstance>> getServiceInstances();
    
    List<String> getServices();
    
    boolean deleteService(final String serviceId);
    
    HashMap<String, ServiceInstance> getInstances(final String serviceId);
    
    boolean deleteInstance(final String serviceId, final String instanceId);
    
    ServiceInstance getInstance(final String uri);
}
