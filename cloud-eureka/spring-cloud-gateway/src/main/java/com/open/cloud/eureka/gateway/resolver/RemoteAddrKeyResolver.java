package com.open.cloud.eureka.gateway.resolver;

import com.open.cloud.eureka.gateway.exception.CommonException;
import com.open.cloud.eureka.gateway.model.ParamInformation;
import com.open.cloud.eureka.gateway.util.LimiterFieldCheckUtil;
import com.open.cloud.eureka.gateway.util.PatternUtil;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class RemoteAddrKeyResolver implements KeyResolver, FieldCheck {

	public static final String BEAN_NAME = "remoteAddrKeyResolver";

	@Override
	public Mono<String> resolve(final ServerWebExchange serverWebExchange) {
		final List<String> remoteAddressList = serverWebExchange.getRequest().getHeaders().get("X-Forwarded-Addr");
		String remoteAddress;
		if (!CollectionUtils.isEmpty(remoteAddressList)) {
			remoteAddress = remoteAddressList.get(0);
		} else {
			remoteAddress = Objects.requireNonNull(serverWebExchange.getRequest().getRemoteAddress()).getAddress().getHostAddress();
		}
		return Mono.just(("remoteAddrKeyResolver@" + remoteAddress));
	}

	@Override
	public void fieldCheck(final List<ParamInformation> params) {
		final HashMap<String, String> paramsMap = new HashMap<String, String>();
		params.forEach(paramInformation -> paramsMap.put(paramInformation.getName(), paramInformation.getValue()));
		if (!LimiterFieldCheckUtil.isInteger(paramsMap.get("custom-redis-rate-limiter.burstCapacity"))) {
			throw new CommonException().setCode("1014").setMsg("Maximum number of visits is incorrect");
		}
		if (!LimiterFieldCheckUtil.isInteger(paramsMap.get("custom-redis-rate-limiter.replenishRate"))) {
			throw new CommonException().setCode("1014").setMsg("The refresh rate is incorrect");
		}
		if (!this.ipCheck(paramsMap.get("custom-redis-rate-limiter.value"))) {
			throw new CommonException().setCode("1014").setMsg("Ip address is incorrect");
		}
	}

	private boolean ipCheck(final String ipAddress) {
		return !StringUtils.isEmpty((Object) ipAddress) && PatternUtil.IP_ADDRESS.matcher(ipAddress).matches();
	}
}
