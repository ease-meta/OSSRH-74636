package com.open.cloud.eureka.gateway.config;

import com.open.cloud.eureka.gateway.filter.AuthSignatureGatewayFilterFactory;
import com.open.cloud.eureka.gateway.filter.CustomRedisRateLimiter;
import com.open.cloud.eureka.gateway.filter.MappingGatewayFilterFactory;
import com.open.cloud.eureka.gateway.filter.SpellGatewayFilterFactory;
import com.open.cloud.eureka.gateway.resolver.GlobalLimiterKeyResolver;
import com.open.cloud.eureka.gateway.resolver.RemoteAddrKeyResolver;
import com.open.cloud.eureka.gateway.resolver.RequestHeaderFixationKeyResolver;
import com.open.cloud.eureka.gateway.resolver.RequestHeaderScopeKeyResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.validation.Validator;

import java.util.List;

@Configuration
public class JupiterConfiguration {
	@Bean(name = {"remoteAddrKeyResolver"})
	@Primary
	public RemoteAddrKeyResolver remoteAddrKeyResolver() {
		return new RemoteAddrKeyResolver();
	}

	@Bean(name = {"requestHeaderFixationKeyResolver"})
	public RequestHeaderFixationKeyResolver requestHeaderFixationKeyResolver() {
		return new RequestHeaderFixationKeyResolver();
	}

	@Bean(name = {"globalLimiterKeyResolver"})
	public GlobalLimiterKeyResolver globalLimiterKeyResolver() {
		return new GlobalLimiterKeyResolver();
	}

	@Bean(name = {"requestHeaderScopeKeyResolver"})
	public RequestHeaderScopeKeyResolver requestHeaderScopeKeyResolver() {
		return new RequestHeaderScopeKeyResolver();
	}

	@Bean
	@Primary
	public CustomRedisRateLimiter customRedisRateLimiter(final ReactiveRedisTemplate<String, String> redisTemplate, @Qualifier("redisRequestRateLimiterScript") final RedisScript<List<Long>> redisScript, final Validator validator) {
		return new CustomRedisRateLimiter(redisTemplate, redisScript, validator);
	}

	@Bean
	public MappingGatewayFilterFactory mappingGatewayFilterFactory() {
		return new MappingGatewayFilterFactory();
	}

	@Bean
	public SpellGatewayFilterFactory spellGatewayFilterFactory() {
		return new SpellGatewayFilterFactory();
	}

	@Bean
	public AuthSignatureGatewayFilterFactory authSignatureGatewayFilterFactory() {
		return new AuthSignatureGatewayFilterFactory();
	}
}