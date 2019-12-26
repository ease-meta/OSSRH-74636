package com.open.cloud.eureka.gateway.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.discovery.EurekaClient;
import com.open.cloud.eureka.gateway.model.InstanceInfoDefinition;
import com.open.cloud.eureka.gateway.repository.InstanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("EurekaInstanceCheck")
@ConfigurationProperties(prefix = "timer")
public class EurekaInstanceCheck extends BasicInstanceCheckTask {
	private static final Logger log;
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	@Qualifier("eurekaClient")
	private EurekaClient eurekaClient;
	@Autowired
	@Qualifier("EurekaInstance")
	private InstanceRepository instanceRepository;
	private int delay;
	private int period;

	public EurekaInstanceCheck() {
		delay = 0;
		period = 30;
	}

	@Override
	public void run() {
		updateLocalInstances();
	}

	@Override
	public void init() {
		eurekaClient.registerEventListener(eurekaEvent -> updateLocalInstances());
		EurekaInstanceCheck.log.info("执行定时任务初始化");
	}

	public void updateLocalInstances() {
		if (EurekaInstanceCheck.log.isInfoEnabled()) {
			EurekaInstanceCheck.log.info("update local instances...");
		}
		final Set<String> servicesSet = new HashSet<String>(discoveryClient.getServices());
		servicesSet.addAll(instanceRepository.getServices());
		final HashMap<String, HashMap<String, ServiceInstance>> localServiceInstances = instanceRepository.getServiceInstances();
		for (final String service : servicesSet) {
			boolean isUpdate = false;
			final List<ServiceInstance> eurekaInstances = (List<ServiceInstance>) discoveryClient.getInstances(service);
			if (localServiceInstances.containsKey(service)) {
				final HashMap<String, ServiceInstance> localInstances = localServiceInstances.get(service);
				if (service.equals("hello-world")) {
					EurekaInstanceCheck.log.info("enter");
				}
				if (eurekaInstances == null || eurekaInstances.isEmpty()) {
					for (final String instanceId : localInstances.keySet()) {
						final InstanceInfoDefinition localInstance = (InstanceInfoDefinition) localInstances.get(instanceId);
						if (localInstance.getInstanceId().equals("GUOYD.digitalchina.com:hello-world:9090)")) {
							EurekaInstanceCheck.log.info("enter 2");
						}
						if (localInstance != null) {
							if (!localInstance.getStatus().equals(InstanceInfoDefinition.InstanceStatus.OUT_OF_SERVICE)) {
								localInstance.setStatus(InstanceInfoDefinition.InstanceStatus.DOWN);
							}
							isUpdate = true;
						}
					}
				} else {
					final HashMap<String, ServiceInstance> tempInstances = new HashMap<String, ServiceInstance>(localInstances);
					for (final ServiceInstance instance : eurekaInstances) {
						final InstanceInfoDefinition localInstance2 = (InstanceInfoDefinition) localInstances.get(((EurekaDiscoveryClient.EurekaServiceInstance) instance).getInstanceInfo().getInstanceId());
						if (localInstance2 == null || (!localInstance2.getStatus().equals(InstanceInfoDefinition.InstanceStatus.UP) && !localInstance2.getStatus().equals(InstanceInfoDefinition.InstanceStatus.OUT_OF_SERVICE))) {
							final InstanceInfoDefinition instanceInfoDefinition = resolverInstance(instance);
							if (EurekaInstanceCheck.log.isInfoEnabled()) {
								EurekaInstanceCheck.log.info("有新实例上线,更新本地实例状态 STATUS: " + instanceInfoDefinition.getStatus() + "   " + instanceInfoDefinition.getInstanceId());
							}
							localInstances.put(instanceInfoDefinition.getInstanceId(), (ServiceInstance) instanceInfoDefinition);
							tempInstances.remove(instanceInfoDefinition.getInstanceId());
							isUpdate = true;
						} else {
							if (!localInstance2.getStatus().equals(InstanceInfoDefinition.InstanceStatus.UP) && !localInstance2.getStatus().equals(InstanceInfoDefinition.InstanceStatus.OUT_OF_SERVICE)) {
								continue;
							}
							tempInstances.remove(localInstance2.getInstanceId());
						}
					}
					if (tempInstances.size() > 0) {
						for (final String instanceId2 : tempInstances.keySet()) {
							final InstanceInfoDefinition localInstance2 = (InstanceInfoDefinition) localInstances.get(instanceId2);
							if (!localInstance2.getStatus().equals(InstanceInfoDefinition.InstanceStatus.OUT_OF_SERVICE) && !localInstance2.getStatus().equals(InstanceInfoDefinition.InstanceStatus.DOWN)) {
								localInstance2.setStatus(InstanceInfoDefinition.InstanceStatus.DOWN);
								if (EurekaInstanceCheck.log.isInfoEnabled()) {
									EurekaInstanceCheck.log.info("eureka下线了实例,新本地缓存对应实例状态为 STATUS: DOWN   " + localInstance2.getInstanceId());
								}
								isUpdate = true;
							}
						}
					}
				}
			} else {
				final HashMap<String, ServiceInstance> newInstances = new HashMap<String, ServiceInstance>();
				for (final ServiceInstance instance2 : eurekaInstances) {
					final InstanceInfoDefinition instanceInfoDefinition2 = resolverInstance(instance2);
					instanceInfoDefinition2.setSecure(instance2.isSecure());
					newInstances.put(instanceInfoDefinition2.getInstanceId(), (ServiceInstance) instanceInfoDefinition2);
				}
				localServiceInstances.put(service, newInstances);
				isUpdate = true;
			}
			if (isUpdate) {
				instanceRepository.saveToRedis(service, localServiceInstances.get(service));
			}
		}
	}

	private InstanceInfoDefinition resolverInstance(final ServiceInstance instance) {
		final String jsonString = JSONObject.toJSONString((Object) instance);
		final JSONObject jsonObject = JSONObject.parseObject(jsonString);
		final Object instanceInfo = jsonObject.get((Object) "instanceInfo");
		final InstanceInfoDefinition instanceInfoDefinition = (InstanceInfoDefinition) JSON.parseObject(JSONObject.toJSONString(instanceInfo), (Class) InstanceInfoDefinition.class);
		instanceInfoDefinition.setAppName(instanceInfoDefinition.getAppName().toLowerCase());
		return instanceInfoDefinition;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(final int delay) {
		this.delay = delay;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(final int period) {
		this.period = period;
	}

	static {
		log = LoggerFactory.getLogger((Class) BasicInstanceCheckTask.class);
	}
}
