package com.open.cloud.eureka.gateway.repository.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.open.cloud.eureka.gateway.exception.CommonException;
import com.open.cloud.eureka.gateway.model.InstanceInfoDefinition;
import com.open.cloud.eureka.gateway.repository.InstanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("EurekaInstance")
public class EurekaInstanceRepository implements InstanceRepository {
	private static final Logger log;
	@Autowired
	private StringRedisTemplate redisTemplate;
	private static final String INSTANCES_INFO = "instances_info";

	@Override
	public HashMap<String, HashMap<String, ServiceInstance>> getServiceInstances() {
		final HashMap<String, HashMap<String, ServiceInstance>> serviceIns = new HashMap<String, HashMap<String, ServiceInstance>>();
		final Set limiterKeys = this.redisTemplate.opsForHash().keys("instances_info");
		for (final Object key : limiterKeys) {
			final Object result = this.redisTemplate.opsForHash().get("instances_info", key);
			if (!ObjectUtils.isEmpty(result)) {
				final HashMap<String, JSONObject> instances = (HashMap<String, JSONObject>) JSON.parseObject(result.toString(), (Class) HashMap.class);
				if (CollectionUtils.isEmpty((Map) instances)) {
					continue;
				}
				final HashMap<String, ServiceInstance> instanceHashMap = new HashMap<String, ServiceInstance>();
				for (final JSONObject instance : instances.values()) {
					final InstanceInfoDefinition instanceInfoDefinition = (InstanceInfoDefinition) JSON.parseObject(JSONObject.toJSONString((Object) instance), (Class) InstanceInfoDefinition.class);
					instanceHashMap.put(instanceInfoDefinition.getInstanceId(), (ServiceInstance) instanceInfoDefinition);
				}
				serviceIns.put(key.toString(), instanceHashMap);
			}
		}
		return serviceIns;
	}

	@Override
	public List<String> getServices() {
		final Set servicesSet = this.getServiceInstances().keySet();
		return (List<String>) Lists.newArrayList((Iterator) servicesSet.iterator());
	}

	@Override
	public ServiceInstance getInstance(final String uri) {
		if (uri == null) {
			EurekaInstanceRepository.log.error("uri cannot be empty");
			throw new CommonException().setCode("1005").setMsg("uri cannot be empty");
		}
		final HashMap<String, HashMap<String, ServiceInstance>> serviceIns = this.getServiceInstances();
		if (!serviceIns.isEmpty()) {
			for (final HashMap<String, ServiceInstance> entry : serviceIns.values()) {
				for (final ServiceInstance instance : entry.values()) {
					if (instance.getUri().toString().equals(uri)) {
						return instance;
					}
				}
			}
		}
		return null;
	}

	@Override
	public HashMap<String, ServiceInstance> getInstances(final String serviceId) {
		final HashMap<String, HashMap<String, ServiceInstance>> serviceIns = this.getServiceInstances();
		if (serviceId != null && serviceIns.keySet().contains(serviceId)) {
			return serviceIns.get(serviceId);
		}
		return null;
	}

	@Override
	public boolean deleteService(final String serviceId) {
		final HashMap<String, HashMap<String, ServiceInstance>> serviceIns = this.getServiceInstances();
		if (serviceIns.containsKey(serviceId)) {
			serviceIns.remove(serviceId);
			this.deleteFromRedis(serviceId);
			return true;
		}
		EurekaInstanceRepository.log.error("service does not exist");
		return false;
	}

	@Override
	public boolean deleteInstance(final String serviceId, final String instanceId) {
		final HashMap<String, HashMap<String, ServiceInstance>> serviceIns = this.getServiceInstances();
		if (!serviceIns.containsKey(serviceId.toLowerCase())) {
			EurekaInstanceRepository.log.error("service does not exist");
			return false;
		}
		final HashMap localInstances = serviceIns.get(serviceId);
		if (localInstances.containsKey(instanceId)) {
			localInstances.remove(instanceId);
			this.saveToRedis(serviceId, serviceIns.get(serviceId));
			return true;
		}
		EurekaInstanceRepository.log.error("Instance does not exists");
		return false;
	}

	public void deleteFromRedis(final String serviceId) {
		this.redisTemplate.opsForHash().delete("instances_info", new Object[]{serviceId});
	}

	@Override
	public void saveToRedis(final String serviceId, final HashMap<String, ServiceInstance> instances) {
		this.redisTemplate.opsForHash().put("instances_info", (Object) serviceId, (Object) JSON.toJSONString((Object) instances));
	}

	static {
		log = LoggerFactory.getLogger((Class) InstanceRepository.class);
	}
}
