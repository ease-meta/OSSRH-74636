package com.open.cloud.eureka.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.open.cloud.eureka.gateway.exception.CommonException;
import com.open.cloud.eureka.gateway.feign.AuthFeignClient;
import com.open.cloud.eureka.gateway.util.PatternUtil;
import feign.FeignException;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

public class AuthSignatureGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthSignatureGatewayFilterFactory.Config> {
	private static final Logger log;
	public static final String PREFIX_KEY = "access:";
	public static final String ENABLED = "enabled";
	private static final int NO_AUTH = 401;
	@Autowired
	StringRedisTemplate redisTemplate;

	@Autowired
	AuthFeignClient authFeignClient;

	public AuthSignatureGatewayFilterFactory() {
		super(Config.class);
	}

	public List<String> shortcutFieldOrder() {
		return Arrays.asList("enabled");
	}

	public GatewayFilter apply(final Config config) {
		return (exchange, chain) -> {
			if (!config.enabled) {
				return chain.filter(exchange);
			}
			final String bearer = exchange.getRequest().getHeaders().getFirst("Authorization");
			if (bearer == null) {
				throw new CommonException().setCode("1013");
			}
			final Matcher matcher = PatternUtil.TOKEN_GET.matcher(bearer);
			matcher.find();
			final String token = matcher.group();
			if (this.redisTemplate.opsForValue().get((Object) "access:".concat(token)) == null) {
				try {
					this.authFeignClient.getAuth(bearer);
				} catch (FeignException e) {
					if (e.status() == 401) {
						AuthSignatureGatewayFilterFactory.log.error("错误码:" + e.status());
						final ServerHttpResponse response = exchange.getResponse();
						response.setStatusCode(HttpStatus.UNAUTHORIZED);
						final HttpHeaders httpHeaders = response.getHeaders();
						httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
						final CommonException exception = new CommonException().setCode("1013");
						final String warningStr = JSONObject.toJSONString((Object) exception);
						final DataBuffer bodyDataBuffer = response.bufferFactory().wrap(warningStr.getBytes());
						return response.writeWith((Publisher) Mono.just((Object) bodyDataBuffer));
					}
					e.printStackTrace();
					throw new CommonException().setCode("1013");
				}
			}
			return chain.filter(exchange.mutate().build());
		};
	}

	static {
		log = LoggerFactory.getLogger((Class) AuthSignatureGatewayFilterFactory.class);
	}

	public static class Config {
		private boolean enabled;

		public Config() {
			this.enabled = true;
		}

		public boolean isEnabled() {
			return this.enabled;
		}

		public void setEnabled(final boolean enabled) {
			this.enabled = enabled;
		}
	}
}
