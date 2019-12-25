package com.open.cloud.eureka.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.open.cloud.eureka.gateway.util.BodyCheckUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.util.UriTemplate;

import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MappingGatewayFilterFactory extends AbstractGatewayFilterFactory<MappingGatewayFilterFactory.Config> {
	private static final Logger log;
	public static final String ENABLED = "enabled";

	public MappingGatewayFilterFactory() {
		super((Class) Config.class);
	}

	public List<String> shortcutFieldOrder() {
		return Arrays.asList("name", "value", "uri");
	}

	public GatewayFilter apply(final Config config) {
		return (exchange, chain) -> {
			final String cache = (String) exchange.getAttribute("requestBody");
			if (StringUtils.isEmpty(cache)) {
				throw new IllegalArgumentException("Request body not contains the SYS_HEADER struct.");
			}
			MappingGatewayFilterFactory.log.info("读取Body体:\n" + cache);
			final LinkedHashMap<String, Object> jsonMap = (LinkedHashMap<String, Object>) JSON.parseObject(cache, (TypeReference) new TypeReference<LinkedHashMap<String, Object>>() {
			}, new Feature[0]);
			final Route route = (Route) exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
			UriTemplate uriTemplate = null;
			if (!BodyCheckUtil.getBodyValue(config.name, config.value, cache)) {
				return chain.filter(exchange);
			}
			uriTemplate = new UriTemplate(route.getUri() + config.uri);
			final ServerHttpRequest req = exchange.getRequest();
			ServerWebExchangeUtils.addOriginalRequestUrl(exchange, req.getURI());
			final URI uri = uriTemplate.expand((Map) Collections.emptyMap());
			final String newPath = uri.getRawPath();
			exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, uri);
			final ServerHttpRequest request = req.mutate().path(newPath).build();
			return chain.filter(exchange.mutate().request(request).build());
		};
	}

	static {
		log = LoggerFactory.getLogger((Class) MappingGatewayFilterFactory.class);
	}

	public static class Config {
		private boolean enabled;
		@NotEmpty
		protected String name;
		@NotEmpty
		protected String value;
		@NotEmpty
		protected String uri;

		public Config() {
			this.enabled = true;
		}

		public boolean isEnabled() {
			return this.enabled;
		}

		public void setEnabled(final boolean enabled) {
			this.enabled = enabled;
		}

		public String getName() {
			return this.name;
		}

		public String getValue() {
			return this.value;
		}

		public String getUri() {
			return this.uri;
		}

		public void setName(final String name) {
			this.name = name;
		}

		public void setValue(final String value) {
			this.value = value;
		}

		public void setUri(final String uri) {
			this.uri = uri;
		}
	}
}
