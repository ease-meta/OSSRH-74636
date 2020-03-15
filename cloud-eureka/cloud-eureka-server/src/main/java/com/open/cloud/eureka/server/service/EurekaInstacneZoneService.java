package com.open.cloud.eureka.server.service;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;
import java.util.Map;

public interface EurekaInstacneZoneService {

	Map<String, List<?>> getServiceByZoneName(String zoneName);

	List<ServiceInstance> getInstances(String serviceName);

	String getEurekaZone();

	boolean setVersionDown(String metaName, String metaValue, String status);
}