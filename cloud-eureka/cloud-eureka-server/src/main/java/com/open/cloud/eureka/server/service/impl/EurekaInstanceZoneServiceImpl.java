package com.open.cloud.eureka.server.service.impl;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContext;
import com.open.cloud.eureka.server.domain.dto.InstanceDTO;
import com.open.cloud.eureka.server.service.EurekaInstacneZoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实现类
 *
 * @author guoyd
 * @create 2019/11/26
 * @since 1.0.0
 */
@Service
@Slf4j
public class EurekaInstanceZoneServiceImpl implements EurekaInstacneZoneService {

	@Autowired
	private Environment env;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	EurekaServerContext eurekaServerContext;


	@Value("#{'${eureka.client.serviceUrl.defaultZone}'.split(',')}")
	private List<String> eurekaAddress;

	private final String statusPath = "apps/{appID}/{instanceID}/status?value={value}";

	private List<ServiceInstance> outInstance = new ArrayList<>();

	String region;
	String availabilityZones;

	@Override
	public Map<String, List<?>> getServiceByZoneName(String zoneName) {
		region = env.getProperty("eureka.client.region");
		availabilityZones = env.getProperty("eureka.client.availability-zones." + region);
		Set<String> servicesSet = new HashSet<>(discoveryClient.getServices());

		Map<String, List<?>> map = new HashMap<>();
		//预编译
		Pattern pattern = Pattern.compile(zoneName);
		for (Application application :
				eurekaServerContext.getRegistry().getApplications().getRegisteredApplications()) {
			//符合zone的实例list
			List<InstanceDTO> insList = new ArrayList<>();
			for (InstanceInfo instance :
					application.getInstances()) {
				//优先获取meta data中的zone信息, 否则取本机zone信息
				String instanceAvailabilityZoneStr = Optional.ofNullable(instance.getMetadata().get("zone"))
						.orElse("NULL");
				Matcher matcher = pattern.matcher(instanceAvailabilityZoneStr);
				while (matcher.find()) {
					insList.add(new InstanceDTO().setInstanceInfo(instance)
							.setAvailabilityZones(instanceAvailabilityZoneStr.split(","))
					);
				}
				map.put(application.getName(), insList);
			}
		}
		return map;
	}

	@Override
	public List<ServiceInstance> getInstances(String serviceName) {
		if (Objects.nonNull(serviceName)) {
			return discoveryClient.getInstances(serviceName);
		} else {
			List<ServiceInstance> list = new LinkedList<>();
			discoveryClient.getServices().forEach(s -> list.addAll(discoveryClient.getInstances(s)));
			return list;
		}
	}

	@Override
	public String getEurekaZone() {
		region = env.getProperty("eureka.client.region");
		availabilityZones = env.getProperty("eureka.client.availability-zones." + region);
		return availabilityZones;
	}

	@Override
	public boolean setVersionDown(String metaName, String metaValue, String status) {
		Applications applications = eurekaServerContext.getRegistry().getApplications();
		RestTemplate restTemplate = new RestTemplate();
		for (Application application :
				applications.getRegisteredApplications()) {
			for (InstanceInfo instance :
					application.getInstances()) {
				if (instance.getMetadata().containsKey(metaName)
						&& instance.getMetadata().get(metaName).equals(metaValue)) {
					restTemplate.put(eurekaAddress.get(0) + statusPath, null, instance.getAppName(), instance.getInstanceId(), status.toUpperCase());
					if (metaValue.equals("up")) {
						outInstance.remove(instance);
					}
					log.info("更新" + instance.getInstanceId() + "状态");
				}
			}
		}
		return true;
	}
}
