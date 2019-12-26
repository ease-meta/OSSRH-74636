package com.open.cloud.eureka.gateway.repository;

import com.alibaba.fastjson.JSON;
import com.open.cloud.eureka.gateway.exception.CommonException;
import com.open.cloud.eureka.gateway.model.BaseAndTempLimiter;
import com.open.cloud.eureka.gateway.model.LimiterDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LimiterRepository
{
    private static final Logger log;
    @Autowired
    private StringRedisTemplate redisTemplate;
    private static final String LIMITER_INFO = "limiter_info";
    
    private Map<String, BaseAndTempLimiter> getLimitersFromRedis() {
        final Map<String, BaseAndTempLimiter> limiters = new ConcurrentHashMap<String, BaseAndTempLimiter>();
        final Set limiterKeys = this.redisTemplate.opsForHash().keys("limiter_info");
        for (final Object key : limiterKeys) {
            final Object result = this.redisTemplate.opsForHash().get("limiter_info", key);
            final BaseAndTempLimiter limiter = (BaseAndTempLimiter)JSON.parseObject(Objects.requireNonNull(result).toString(), (Class)BaseAndTempLimiter.class);
            limiters.put(key.toString(), limiter);
        }
        return limiters;
    }
    
    public BaseAndTempLimiter getBaseAndTempLimiter(final String limiterInfoKey) {
        return this.getLimitersFromRedis().get(limiterInfoKey);
    }
    
    public Map<String, BaseAndTempLimiter> getLimiters() {
        return this.getLimitersFromRedis();
    }
    
    public List<LimiterDefinition> getLimitersByServiceId(final String limiterInfoKey, final String type) {
        final Map<String, BaseAndTempLimiter> limiters = this.getLimitersFromRedis();
        if (limiters.containsKey(limiterInfoKey)) {
            List<LimiterDefinition> localLimiters;
            if ("base".equals(type)) {
                localLimiters = limiters.get(limiterInfoKey).getBaseLimiter();
            }
            else {
                localLimiters = limiters.get(limiterInfoKey).getTempLimiter();
            }
            return localLimiters;
        }
        return Collections.emptyList();
    }
    
    public void deleteLimiter(final String limiterInfoKey, final String type, final String id) {
        final Map<String, BaseAndTempLimiter> limiters = this.getLimitersFromRedis();
        if (!limiters.containsKey(limiterInfoKey)) {
            LimiterRepository.log.error(String.format("service %s does not exist", limiterInfoKey));
            throw new CommonException().setCode("1017").setMsg(String.format("service %s does not exist", limiterInfoKey));
        }
        List<LimiterDefinition> localLimiters;
        if ("base".equals(type)) {
            localLimiters = limiters.get(limiterInfoKey).getBaseLimiter();
        }
        else {
            localLimiters = limiters.get(limiterInfoKey).getTempLimiter();
        }
        boolean flag = false;
        if (localLimiters == null) {
            LimiterRepository.log.error(String.format("%s Limiter does not exist", type));
            throw new CommonException().setCode("1017").setMsg(String.format("%s Limiter does not exist", type));
        }
        for (final LimiterDefinition limiter : localLimiters) {
            if (limiter.getId().equals(id)) {
                localLimiters.remove(limiter);
                this.saveToRedis(limiterInfoKey, limiters.get(limiterInfoKey));
                flag = true;
                break;
            }
        }
        if (!flag) {
            LimiterRepository.log.error(String.format("Limiter %s does not exist", id));
            throw new CommonException().setCode("1017").setMsg(String.format("Limiter %s does not exist", id));
        }
    }
    
    public void addLimiter(final String serviceId, final String type, final LimiterDefinition limiter) {
        final Map<String, BaseAndTempLimiter> limiters = this.getLimitersFromRedis();
        this.repeatCheck(serviceId, type, limiter);
        limiter.setId(UUID.randomUUID().toString());
        List<LimiterDefinition> localLimiters;
        if (limiters.containsKey(serviceId)) {
            if ("base".equals(type)) {
                localLimiters = limiters.get(serviceId).getBaseLimiter();
            }
            else {
                localLimiters = limiters.get(serviceId).getTempLimiter();
            }
        }
        else {
            final BaseAndTempLimiter baseAndTempLimiter = new BaseAndTempLimiter();
            limiters.put(serviceId, baseAndTempLimiter);
            if ("base".equals(type)) {
                localLimiters = baseAndTempLimiter.getBaseLimiter();
            }
            else {
                localLimiters = baseAndTempLimiter.getTempLimiter();
            }
        }
        localLimiters.add(limiter);
        this.saveToRedis(serviceId, limiters.get(serviceId));
    }
    
    public void addLimiterList(final String serviceId, final String type, final List<LimiterDefinition> limiterList) {
        for (final LimiterDefinition limiter : limiterList) {
            this.addLimiter(serviceId, type, limiter);
        }
    }
    
    public void updateLimiter(final String serviceId, final String type, final LimiterDefinition limiter) {
        final Map<String, BaseAndTempLimiter> limiters = this.getLimitersFromRedis();
        if (limiters.containsKey(serviceId)) {
            List<LimiterDefinition> localLimiters;
            if ("base".equals(type)) {
                localLimiters = limiters.get(serviceId).getBaseLimiter();
            }
            else {
                localLimiters = limiters.get(serviceId).getTempLimiter();
            }
            for (final LimiterDefinition localLimiter : localLimiters) {
                if (localLimiter.getId().equals(limiter.getId())) {
                    Collections.replaceAll(localLimiters, localLimiter, limiter);
                    break;
                }
            }
            this.saveToRedis(serviceId, limiters.get(serviceId));
            return;
        }
        throw new CommonException().setCode("1017").setMsg(String.format("service %s does not exist", serviceId));
    }
    
    public void updateLimiterList(final String serviceId, final String type, final List<LimiterDefinition> limiterList) {
        for (final LimiterDefinition limiterDefinition : limiterList) {
            this.updateLimiter(serviceId, type, limiterDefinition);
        }
    }
    
    private void repeatCheck(final String serviceId, final String type, final LimiterDefinition limiter) {
        final Map<String, BaseAndTempLimiter> limiters = this.getLimitersFromRedis();
        if (limiters.containsKey(serviceId)) {
            List<LimiterDefinition> limiterList;
            if ("base".equals(type)) {
                limiterList = limiters.get(serviceId).getBaseLimiter();
            }
            else {
                limiterList = limiters.get(serviceId).getTempLimiter();
            }
            for (final LimiterDefinition localLimiter : limiterList) {
                if (localLimiter.equals(limiter)) {
                    throw new CommonException().setCode("1001").setMsg("Limiter already exists");
                }
            }
        }
    }
    
    public void deleteServiceLimiter(final String serviceName) {
        this.deleteFromRedis(serviceName);
    }
    
    private void saveToRedis(final String serviceId, final BaseAndTempLimiter baseAndTempLimiter) {
        if (baseAndTempLimiter != null) {
            this.redisTemplate.opsForHash().put("limiter_info", (Object)serviceId, (Object)JSON.toJSONString((Object)baseAndTempLimiter));
        }
    }
    
    private void deleteFromRedis(final String serviceId) {
        this.redisTemplate.opsForHash().delete("limiter_info", new Object[] { serviceId });
    }
    
    static {
        log = LoggerFactory.getLogger((Class)LimiterRepository.class);
    }
}
