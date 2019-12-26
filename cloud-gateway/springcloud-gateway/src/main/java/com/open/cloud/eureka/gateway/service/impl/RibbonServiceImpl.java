package com.open.cloud.eureka.gateway.service.impl;

import com.alibaba.fastjson.JSON;
import com.netflix.client.config.IClientConfig;
import com.open.cloud.eureka.gateway.config.RibbonDefaultConfiguration;
import com.open.cloud.eureka.gateway.model.RibbonDefinition;
import com.open.cloud.eureka.gateway.model.RibbonParamDefinition;
import com.open.cloud.eureka.gateway.service.LoadBalancerService;
import com.open.cloud.eureka.gateway.util.RedisCacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.named.NamedContextFactory;
import org.springframework.cloud.context.scope.GenericScope;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RibbonServiceImpl implements LoadBalancerService {
	private static final Logger log;
	@Autowired
	RedisCacheUtil redisCacheUtil;
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired(required = false)
	private IClientConfig config;
	@Autowired
	RibbonDefaultConfiguration rconfig;
	@Value("${ribbon.NFLoadBalancerRuleClassName:com.netflix.loadbalancer.RoundRobinRule}")
	private String defaultClassName;

	@Override
	public List<Object> getLoadBalancersAll() {
		final Map<String, String> map = (Map<String, String>) this.redisCacheUtil.getRedisCache("ribbon");
		final List<Object> list = new ArrayList<Object>();
		for (final String key : map.keySet()) {
			list.add(new RibbonParamDefinition().setRibbonName(key).setRibbonClass(map.get(key)));
		}
		return list;
	}

	@Override
	public Object getLoadBalancerByName(final String name) {
		final Map<String, String> map = (Map<String, String>) this.redisCacheUtil.getRedisCache("ribbon_service");
		RibbonDefinition ribbonDefinition = null;
		for (final String key : map.keySet()) {
			if (key.equals(name)) {
				ribbonDefinition = (RibbonDefinition) JSON.parseObject((String) map.get(key), (Class) RibbonDefinition.class);
				ribbonDefinition.setRibbonName(this.findRibbonName(ribbonDefinition.getRibbonClass()));
				break;
			}
		}
		return Optional.ofNullable(ribbonDefinition).orElse(this.getDefaultRibbonDefinition().setServiceName(name));
	}

	@Override
	public Boolean setLoadBalancer(final Object loadBalancerDefiniton) {
		final RibbonDefinition ribbonDefinition = (RibbonDefinition) loadBalancerDefiniton;
		try {
			final NamedContextFactory contextFactory = (NamedContextFactory) this.applicationContext.getAutowireCapableBeanFactory().getBean((Class) NamedContextFactory.class);
			final GenericScope genericScope = (GenericScope) this.applicationContext.getAutowireCapableBeanFactory().getBean((Class) GenericScope.class);
			contextFactory.destroy();
		} catch (Exception e) {
			RibbonServiceImpl.log.error("Set load balancer error.{}", (Throwable) e);
		}
		this.redisCacheUtil.saveRedisCache("ribbon_service", ribbonDefinition.getServiceName(), JSON.toJSONString((Object) ribbonDefinition));
		return true;
	}

	String findRibbonName(final String ribbonClass) {
		final Map<String, String> map = (Map<String, String>) this.redisCacheUtil.getRedisCache("ribbon");
		for (final String key : map.keySet()) {
			if (map.get(key).equals(ribbonClass)) {
				return key;
			}
		}
		return null;
	}

	public RibbonDefinition getDefaultRibbonDefinition() {
		final Map<String, String> map = (Map<String, String>) this.redisCacheUtil.getRedisCache("ribbon");
		RibbonDefinition ribbonDefinition = null;
		for (final String key : map.keySet()) {
			if (map.get(key).equals(this.defaultClassName)) {
				ribbonDefinition = new RibbonDefinition().setRibbonName(key).setRibbonClass(map.get(key));
				break;
			}
		}
		return ribbonDefinition;
	}

	static {
		log = LoggerFactory.getLogger((Class) RibbonServiceImpl.class);
	}
}
