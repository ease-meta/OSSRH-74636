package com.open.cloud.eureka.gateway.service.impl;

import com.open.cloud.eureka.gateway.model.InstanceInfoDefinition;
import com.open.cloud.eureka.gateway.repository.InstanceRepository;
import com.open.cloud.eureka.gateway.service.InstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Service("EurekaInstanceService")
public class EurekaInstanceServiceImpl implements InstanceService
{
    private static final Logger log;
    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaAddress;
    @Autowired
    @Qualifier("EurekaInstance")
    private InstanceRepository instanceRepository;
    private RestTemplate restTemplate;
    private final String statusPath = "apps/{appID}/{instanceID}/status?value={value}";
    private final String deletePath = "apps/";
    
    public EurekaInstanceServiceImpl() {
        this.restTemplate = new RestTemplate();
    }
    
    public void afterPropertiesSet() {
        if (this.eurekaAddress.lastIndexOf("/") < this.eurekaAddress.length() - 1) {
            this.eurekaAddress += "/";
        }
    }
    
    @Override
    public boolean updateInstanceStatus(final String serviceId, final String instanceId, final String status) {
        try {
            final HashMap<String, ServiceInstance> instances = this.instanceRepository.getInstances(serviceId.toLowerCase());
            if (instances.keySet().contains(instanceId)) {
                final InstanceInfoDefinition instance = (InstanceInfoDefinition)instances.get(instanceId);
                instance.setStatus(InstanceInfoDefinition.InstanceStatus.toEnum(status));
                this.instanceRepository.saveToRedis(serviceId, instances);
                this.restTemplate.put(this.eurekaAddress + "apps/{appID}/{instanceID}/status?value={value}", (Object)null, new Object[] { serviceId, instanceId, status });
                return true;
            }
            EurekaInstanceServiceImpl.log.error("service or instance does not exist");
            return false;
        }
        catch (Exception e) {
            EurekaInstanceServiceImpl.log.error("service or instance does not exist");
            return false;
        }
    }
    
    @Override
    public HashMap<String, HashMap<String, ServiceInstance>> getServiceInstances() {
        return this.instanceRepository.getServiceInstances();
    }
    
    @Override
    public List<String> getServices() {
        return this.instanceRepository.getServices();
    }
    
    @Override
    public boolean deleteService(final String serviceId) {
        return this.instanceRepository.deleteService(serviceId);
    }
    
    @Override
    public HashMap<String, ServiceInstance> getInstances(final String serviceId) {
        return this.instanceRepository.getInstances(serviceId);
    }
    
    @Override
    public boolean deleteInstance(final String serviceId, final String instanceId) {
        if (this.instanceRepository.deleteInstance(serviceId, instanceId)) {
            try {
                this.restTemplate.delete(this.eurekaAddress + "apps/" + serviceId + "/" + instanceId, new Object[0]);
            }
            catch (Exception e) {
                EurekaInstanceServiceImpl.log.warn("The instance has been offline");
            }
            return true;
        }
        return false;
    }
    
    @Override
    public ServiceInstance getInstance(final String uri) {
        return this.instanceRepository.getInstance(uri);
    }
    
    static {
        log = LoggerFactory.getLogger((Class)EurekaInstanceServiceImpl.class);
    }
}
