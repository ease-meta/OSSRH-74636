package com.open.cloud.eureka.gateway.repository;

import com.alibaba.fastjson.JSON;
import com.open.cloud.eureka.gateway.model.KeyResolverDefinition;
import com.open.cloud.eureka.gateway.model.LimiterDefinition;
import com.open.cloud.eureka.gateway.model.LimiterFilterDefinition;
import com.open.cloud.eureka.gateway.model.RateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LimiterConfigRepository {
	private static final Logger log;
	@Autowired
	private StringRedisTemplate redisTemplate;
	private final String LIMITER_CONFIG = "limiter_config";

	private Map<String, KeyResolverDefinition> getLimiterConfigFromRedis() {
		final Map<String, KeyResolverDefinition> limiterConfig = new ConcurrentHashMap<String, KeyResolverDefinition>();
		final Set limiterConfigKeys = this.redisTemplate.opsForHash().keys("limiter_config");
		for (final Object key : limiterConfigKeys) {
			final Object result = this.redisTemplate.opsForHash().get("limiter_config", key);
			final KeyResolverDefinition keyResolverDefinition = (KeyResolverDefinition) JSON.parseObject(Objects.requireNonNull(result).toString(), (Class) KeyResolverDefinition.class);
			limiterConfig.put(key.toString(), keyResolverDefinition);
		}
		return limiterConfig;
	}

	public KeyResolverDefinition getLimiterConfig(final String routeId) {
		final Map<String, KeyResolverDefinition> limiterConfig = this.getLimiterConfigFromRedis();
		return limiterConfig.getOrDefault(routeId, null);
	}

	public LimiterFilterDefinition getLimiterInfo(final String routeId, final String keyResolver) {
		final Map<String, KeyResolverDefinition> limiterConfig = this.getLimiterConfigFromRedis();
		if (limiterConfig.containsKey(routeId)) {
			final HashMap<String, LimiterFilterDefinition> keyResolvers = limiterConfig.get(routeId).getKeyResolvers();
			return keyResolvers.getOrDefault(keyResolver, null);
		}
		return null;
	}

	public void updateLimiterConfig(final String routeId, final List<LimiterDefinition> baseLimiters) {
		final Map<String, KeyResolverDefinition> limiterConfig = this.getLimiterConfigFromRedis();
		final KeyResolverDefinition keyResolverDefinition = new KeyResolverDefinition();
		final HashMap<String, LimiterFilterDefinition> keyResolvers = keyResolverDefinition.getKeyResolvers();
		for (final LimiterDefinition limiterDefinition : baseLimiters) {
			this.updateLimiterFilterDefinition(keyResolvers, limiterDefinition);
		}
		limiterConfig.put(routeId, keyResolverDefinition);
		this.saveToRedis(routeId, limiterConfig.get(routeId));
	}

	private RateConfig limiterToRate(final LimiterDefinition limiterDefinition) {
		final RateConfig rateConfig = new RateConfig();
		rateConfig.setBurstCapacity(limiterDefinition.getFilter().getArgs().get("custom-redis-rate-limiter.burstCapacity"));
		rateConfig.setReplenishRate(limiterDefinition.getFilter().getArgs().get("custom-redis-rate-limiter.replenishRate"));
		rateConfig.setKeyResolver(limiterDefinition.getFilter().getArgs().get("key-resolver"));
		rateConfig.setKey(limiterDefinition.getFilter().getArgs().get("custom-redis-rate-limiter.key"));
		rateConfig.setValue(limiterDefinition.getFilter().getArgs().get("custom-redis-rate-limiter.value"));
		rateConfig.setScope(limiterDefinition.getFilter().getArgs().get("custom-redis-rate-limiter.scope"));
		return rateConfig;
	}

	private void updateLimiterFilterDefinition(final HashMap<String, LimiterFilterDefinition> keyResolvers, final LimiterDefinition limiterDefinition) {
		if (keyResolvers.containsKey(limiterDefinition.getFilter().getArgs().get("key-resolver"))) {
			final LimiterFilterDefinition limiterFilterDefinition = keyResolvers.get(limiterDefinition.getFilter().getArgs().get("key-resolver"));
			final HashMap<String, RateConfig> limiterInfo = limiterFilterDefinition.getLimiterInfo();
			final RateConfig rateConfig = this.limiterToRate(limiterDefinition);
			limiterInfo.put(rateConfig.getValue(), rateConfig);
		} else {
			final LimiterFilterDefinition limiterFilterDefinition = new LimiterFilterDefinition();
			final HashMap<String, RateConfig> limiterInfo = limiterFilterDefinition.getLimiterInfo();
			final RateConfig rateConfig = this.limiterToRate(limiterDefinition);
			limiterInfo.put(rateConfig.getKey() + "@" + rateConfig.getValue(), rateConfig);
			keyResolvers.put(limiterDefinition.getFilter().getArgs().get("key-resolver"), limiterFilterDefinition);
		}
	}

	public void deleteLimiterConfig(final String routeId) {
		this.deleteFromRedis(routeId);
	}

	private void saveToRedis(final String routeId, final KeyResolverDefinition keyResolverDefinition) {
		if (keyResolverDefinition != null) {
			this.redisTemplate.opsForHash().put("limiter_config", routeId, JSON.toJSONString((Object) keyResolverDefinition));
		}
	}

	private void deleteFromRedis(final String routeId) {
		this.redisTemplate.opsForHash().delete("limiter_config", new Object[]{routeId});
	}

	public StringRedisTemplate getRedisTemplate() {
		return this.redisTemplate;
	}

	public String getLIMITER_CONFIG() {
		this.getClass();
		return "limiter_config";
	}

	public void setRedisTemplate(final StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof LimiterConfigRepository)) {
			return false;
		}
		final LimiterConfigRepository other = (LimiterConfigRepository) o;
		if (!other.canEqual(this)) {
			return false;
		}
		final Object this$redisTemplate = this.getRedisTemplate();
		final Object other$redisTemplate = other.getRedisTemplate();
		Label_0065:
		{
			if (this$redisTemplate == null) {
				if (other$redisTemplate == null) {
					break Label_0065;
				}
			} else if (this$redisTemplate.equals(other$redisTemplate)) {
				break Label_0065;
			}
			return false;
		}
		final Object this$LIMITER_CONFIG = this.getLIMITER_CONFIG();
		final Object other$LIMITER_CONFIG = other.getLIMITER_CONFIG();
		if (this$LIMITER_CONFIG == null) {
			if (other$LIMITER_CONFIG == null) {
				return true;
			}
		} else if (this$LIMITER_CONFIG.equals(other$LIMITER_CONFIG)) {
			return true;
		}
		return false;
	}

	protected boolean canEqual(final Object other) {
		return other instanceof LimiterConfigRepository;
	}

	@Override
	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $redisTemplate = this.getRedisTemplate();
		result = result * 59 + (($redisTemplate == null) ? 43 : $redisTemplate.hashCode());
		final Object $LIMITER_CONFIG = this.getLIMITER_CONFIG();
		result = result * 59 + (($LIMITER_CONFIG == null) ? 43 : $LIMITER_CONFIG.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "LimiterConfigRepository(redisTemplate=" + this.getRedisTemplate() + ", LIMITER_CONFIG=" + this.getLIMITER_CONFIG() + ")";
	}

	static {
		log = LoggerFactory.getLogger((Class) LimiterConfigRepository.class);
	}
}
