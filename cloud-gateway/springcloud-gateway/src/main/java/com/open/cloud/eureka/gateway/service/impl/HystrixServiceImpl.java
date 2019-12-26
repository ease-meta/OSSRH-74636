package com.open.cloud.eureka.gateway.service.impl;

import com.alibaba.fastjson.JSON;
import com.netflix.config.ConfigurationManager;
import com.open.cloud.eureka.gateway.exception.CommonException;
import com.open.cloud.eureka.gateway.model.HystrixDefinition;
import com.open.cloud.eureka.gateway.service.CircuitBreakerService;
import com.open.cloud.eureka.gateway.util.RedisCacheUtil;
import org.apache.commons.configuration.AbstractConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HystrixServiceImpl implements CircuitBreakerService
{
    private static final Logger log;
    @Autowired
    RedisCacheUtil redisCacheUtil;
    
    @Override
    public Boolean excuteCircuitBreaker(final Object breakerDefinition, final Boolean flag) {
        final HystrixDefinition hystrixDefinition = (HystrixDefinition)breakerDefinition;
        final Map<String, String> map = (Map<String, String>)this.redisCacheUtil.getRedisCache("hystrix");
        if (!flag && map.containsKey(hystrixDefinition.getName())) {
            throw new CommonException().setCode("1018");
        }
        try {
            final String commandKey = hystrixDefinition.getName();
            final String prefix = "hystrix.command." + commandKey + ".";
            final AbstractConfiguration config = ConfigurationManager.getConfigInstance();
            if (hystrixDefinition.getTimeoutInMilliseconds() != null) {
                config.setProperty(prefix + "execution.isolation.thread.timeoutInMilliseconds", (Object)hystrixDefinition.getTimeoutInMilliseconds());
            }
            if (hystrixDefinition.getMaxConcurrentRequests() != null) {
                config.setProperty(prefix + "execution.isolation.semaphore.maxConcurrentRequests", (Object)hystrixDefinition.getMaxConcurrentRequests());
            }
        }
        catch (Exception e) {
            HystrixServiceImpl.log.error("执行配置存取失败", (Throwable)e);
        }
        this.redisCacheUtil.saveRedisCache("hystrix", hystrixDefinition.getName(), JSON.toJSONString((Object)hystrixDefinition));
        return true;
    }
    
    @Override
    public List<HystrixDefinition> getCircuitBreakers() {
        final List<HystrixDefinition> list = new ArrayList<HystrixDefinition>();
        final Map<String, String> map = (Map<String, String>)this.redisCacheUtil.getRedisCache("hystrix");
        for (final String key : map.keySet()) {
            list.add((HystrixDefinition)JSON.parseObject((String)map.get(key), (Class)HystrixDefinition.class));
        }
        return list;
    }
    
    @Override
    public void initCircuitBreakers() {
        HystrixServiceImpl.log.info("初始化Hystrix熔断配置");
        final List<HystrixDefinition> list = this.getCircuitBreakers();
        for (final HystrixDefinition hystrixDefinition : list) {
            this.excuteCircuitBreaker(hystrixDefinition, true);
        }
    }
    
    @Override
    public Boolean delCircuitBreaker(final String name) {
        return this.redisCacheUtil.delRedisCache("hystrix", name);
    }
    
    static {
        log = LoggerFactory.getLogger((Class)HystrixServiceImpl.class);
    }
}
