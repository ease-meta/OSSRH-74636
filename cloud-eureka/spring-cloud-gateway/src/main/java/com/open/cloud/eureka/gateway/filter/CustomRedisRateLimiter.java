package com.open.cloud.eureka.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.open.cloud.eureka.gateway.model.LimiterFilterDefinition;
import com.open.cloud.eureka.gateway.model.RateConfig;
import com.open.cloud.eureka.gateway.repository.LimiterConfigRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.ratelimit.AbstractRateLimiter;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomRedisRateLimiter
		extends AbstractRateLimiter<CustomRedisRateLimiter.Config>
		implements ApplicationContextAware, Ordered {
	@Deprecated
	public static final String REPLENISH_RATE_KEY = "replenishRate";
	@Deprecated
	public static final String BURST_CAPACITY_KEY = "burstCapacity";
	@Autowired
	private LimiterConfigRepository limiterConfigRepository;
	public static final String CONFIGURATION_PROPERTY_NAME = "custom-redis-rate-limiter";
	public static final String REDIS_SCRIPT_NAME = "redisRequestRateLimiterScript";
	public static final String REMAINING_HEADER = "X-RateLimit-Remaining";
	public static final String REPLENISH_RATE_HEADER = "X-RateLimit-Replenish-Rate";
	public static final String BURST_CAPACITY_HEADER = "X-RateLimit-Burst-Capacity";
	private Log log = LogFactory.getLog(getClass());

	private ReactiveRedisTemplate<String, String> redisTemplate;
	private RedisScript<List<Long>> script;
	private AtomicBoolean initialized = new AtomicBoolean(false);


	private Config defaultConfig;


	private boolean includeHeaders = true;


	private String remainingHeader = "X-RateLimit-Remaining";


	private String replenishRateHeader = "X-RateLimit-Replenish-Rate";


	private String burstCapacityHeader = "X-RateLimit-Burst-Capacity";


	public CustomRedisRateLimiter(ReactiveRedisTemplate<String, String> redisTemplate, RedisScript<List<Long>> script, Validator validator) {
		super(Config.class, "custom-redis-rate-limiter", validator);
		this.redisTemplate = redisTemplate;
		this.script = script;
		this.initialized.compareAndSet(false, true);
	}

	public boolean isIncludeHeaders() {
		return this.includeHeaders;
	}


	public void setIncludeHeaders(boolean includeHeaders) {
		this.includeHeaders = includeHeaders;
	}


	public String getRemainingHeader() {
		return this.remainingHeader;
	}


	public void setRemainingHeader(String remainingHeader) {
		this.remainingHeader = remainingHeader;
	}


	public String getReplenishRateHeader() {
		return this.replenishRateHeader;
	}


	public void setReplenishRateHeader(String replenishRateHeader) {
		this.replenishRateHeader = replenishRateHeader;
	}


	public String getBurstCapacityHeader() {
		return this.burstCapacityHeader;
	}


	public void setBurstCapacityHeader(String burstCapacityHeader) {
		this.burstCapacityHeader = burstCapacityHeader;
	}


	public void setApplicationContext(ApplicationContext context) throws BeansException {
		if (this.initialized.compareAndSet(false, true)) {
			this.redisTemplate = (ReactiveRedisTemplate<String, String>) context.getBean("stringReactiveRedisTemplate", ReactiveRedisTemplate.class);
			this.script = (RedisScript<List<Long>>) context.getBean("redisRequestRateLimiterScript", RedisScript.class);
			if ((context.getBeanNamesForType(Validator.class)).length > 0) {
				setValidator((Validator) context.getBean(Validator.class));
			}
		}
	}


	Config getDefaultConfig() {
		return this.defaultConfig;
	}


	public Mono<RateLimiter.Response> isAllowed(String routeId, String id) {
		try {
			String[] strings = id.split("@");
			String resolver = strings[0];
			String limiterValue = strings[1];
			if (!this.initialized.get()) {
				throw new IllegalStateException("CustomRedisRateLimiter is not initialized");
			}

			Config routeConfig = (Config) getConfig().getOrDefault(routeId, this.defaultConfig);

			if (routeConfig == null) {
				throw new IllegalArgumentException("No Configuration found for route " + routeId);
			}


			int replenishRate = 0;


			int burstCapacity = 0;

			try {
				List<String> keys = getKeys(id);
				String resolverType = "#{@" + resolver + "}";

				LimiterFilterDefinition limiterFilterDefinition = this.limiterConfigRepository.getLimiterInfo(routeId, resolverType);

				if (limiterFilterDefinition != null) {
					HashMap<String, String> requestHeader;
					Collection<RateConfig> rateConfigCollection;
					switch (resolver) {
						case "requestHeaderFixationKeyResolver":
							requestHeader = (HashMap<String, String>) JSON.parseObject(limiterValue, HashMap.class);
							rateConfigCollection = limiterFilterDefinition.getLimiterInfo().values();
							if (!CollectionUtils.isEmpty(rateConfigCollection)) {
								int level = 0;
								for (RateConfig rateConfig : rateConfigCollection) {
									List<String> keyList = Arrays.asList(rateConfig.getKey().split("-"));
									StringBuilder stringBuilder = new StringBuilder();
									keyList.forEach(key ->
											stringBuilder.append((String) requestHeader.get(key)).append("-"));

									stringBuilder.deleteCharAt(stringBuilder.length() - 1);
									if (stringBuilder.toString().equals(rateConfig.getValue()) &&
											keyList.size() > level) {
										level = keyList.size();
										replenishRate = Integer.parseInt(rateConfig.getReplenishRate());
										burstCapacity = Integer.parseInt(rateConfig.getBurstCapacity());
									}
								}

								if (level == 0)
									return Mono.just(new RateLimiter.Response(true, getHeaders(routeConfig, Long.valueOf(-1L))));
								break;
							}
							return Mono.just(new RateLimiter.Response(true, getHeaders(routeConfig, Long.valueOf(-1L))));


						case "requestHeaderScopeKeyResolver":
							requestHeader = (HashMap<String, String>) JSON.parseObject(limiterValue, HashMap.class);
							rateConfigCollection = limiterFilterDefinition.getLimiterInfo().values();
							if (!CollectionUtils.isEmpty(rateConfigCollection)) {
								boolean hit = false;
								for (RateConfig rateConfig : rateConfigCollection) {
									String key = rateConfig.getKey();
									String value = rateConfig.getValue();
									String[] values = value.split("-");
									String start = values[0];
									String end = values[1];
									String headerKey = requestHeader.get(key);
									String result = headerKey.substring(0, Integer.parseInt(rateConfig.getScope()));
									if (result.compareTo(start) > 0 && result.compareTo(end) <= 0) {
										hit = true;
										replenishRate = Integer.parseInt(rateConfig.getReplenishRate());
										burstCapacity = Integer.parseInt(rateConfig.getBurstCapacity());
									}
								}
								if (!hit)
									return Mono.just(new RateLimiter.Response(true, getHeaders(routeConfig, Long.valueOf(-1L))));
								break;
							}
							return Mono.just(new RateLimiter.Response(true, getHeaders(routeConfig, Long.valueOf(-1L))));


						case "remoteAddrKeyResolver":
							rateConfigCollection = limiterFilterDefinition.getLimiterInfo().values();
							if (!CollectionUtils.isEmpty(rateConfigCollection)) {
								for (RateConfig rateConfig : rateConfigCollection) {
									String value = rateConfig.getValue();
									if (Objects.equals(limiterValue, value)) {
										replenishRate = Integer.parseInt(rateConfig.getReplenishRate());
										burstCapacity = Integer.parseInt(rateConfig.getBurstCapacity());
									}
								}
								break;
							}
							return Mono.just(new RateLimiter.Response(true, getHeaders(routeConfig, Long.valueOf(-1L))));


						case "globalLimiterKeyResolver":
							rateConfigCollection = limiterFilterDefinition.getLimiterInfo().values();
							if (!CollectionUtils.isEmpty(rateConfigCollection)) {
								for (RateConfig rateConfig : rateConfigCollection) {
									if (burstCapacity == 0 || burstCapacity > Integer.parseInt(rateConfig.getReplenishRate())) {
										replenishRate = Integer.parseInt(rateConfig.getReplenishRate());
										burstCapacity = Integer.parseInt(rateConfig.getBurstCapacity());
									}
								}
								break;
							}
							return Mono.just(new RateLimiter.Response(true, getHeaders(routeConfig, Long.valueOf(-1L))));
					}


				} else {
					return Mono.just(new RateLimiter.Response(true, getHeaders(routeConfig, Long.valueOf(-1L))));
				}


				List<String> scriptArgs = Arrays.asList(new String[]{Integer.toString(replenishRate), Integer.toString(burstCapacity),
						Long.toString(Instant.now().getEpochSecond()), "1"});

				Flux<List<Long>> flux = this.redisTemplate.execute(this.script, keys, scriptArgs);
				return flux.onErrorResume(throwable -> Flux.just(Arrays.asList(new Long[]{Long.valueOf(1L), Long.valueOf(-1L)
				}))).reduce(new ArrayList(), (longs, l) -> {
					longs.addAll(l);
					return longs;
				}).map(results -> {
					boolean allowed = (((Long) results.get(0)).longValue() == 1L);
					Long tokensLeft = (Long) results.get(1);
					RateLimiter.Response response = new RateLimiter.Response(allowed, getHeaders(routeConfig, tokensLeft));
					if (this.log.isDebugEnabled()) {
						this.log.debug("response: " + response);
					}
					return response;
				});
			} catch (Exception e) {


				this.log.error("Error determining if user allowed from redis", e);

				return Mono.just(new RateLimiter.Response(true, getHeaders(routeConfig, Long.valueOf(-1L))));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@NotNull
	public HashMap<String, String> getHeaders(Config config, Long tokensLeft) {
		HashMap<String, String> headers = new HashMap<>();
		headers.put(this.remainingHeader, tokensLeft.toString());
		headers.put(this.replenishRateHeader, String.valueOf(config.getReplenishRate()));
		headers.put(this.burstCapacityHeader, String.valueOf(config.getBurstCapacity()));
		return headers;
	}


	static List<String> getKeys(String id) {
		String prefix = "request_rate_limiter.{" + id;


		String tokenKey = prefix + "}.tokens";
		String timestampKey = prefix + "}.timestamp";
		return Arrays.asList(new String[]{tokenKey, timestampKey});
	}


	public int getOrder() {
		return 20;
	}

	@Validated
	public static class Config {
		@Min(1L)
		private int replenishRate;
		@Min(1L)
		private int burstCapacity = 1;


		public int getReplenishRate() {
			return this.replenishRate;
		}


		public Config setReplenishRate(int replenishRate) {
			this.replenishRate = replenishRate;
			return this;
		}


		public int getBurstCapacity() {
			return this.burstCapacity;
		}


		public Config setBurstCapacity(int burstCapacity) {
			this.burstCapacity = burstCapacity;
			return this;
		}


		public String toString() {
			return "Config{replenishRate=" + this.replenishRate + ", burstCapacity=" + this.burstCapacity + '}';
		}
	}
}
