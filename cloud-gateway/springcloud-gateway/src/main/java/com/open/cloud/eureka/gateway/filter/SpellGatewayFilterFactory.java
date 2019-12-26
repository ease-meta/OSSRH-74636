package com.open.cloud.eureka.gateway.filter;

import io.netty.buffer.ByteBufAllocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import reactor.core.publisher.Flux;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SpellGatewayFilterFactory extends AbstractGatewayFilterFactory<SpellGatewayFilterFactory.Config> {
	private static final Logger log;
	public static final String ENABLED = "enabled";

	public SpellGatewayFilterFactory() {
		super((Class) Config.class);
	}

	public List<String> shortcutFieldOrder() {
		return Arrays.asList("enabled");
	}

	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			if (config.enabled) {
				return chain.filter(exchange);
			}

			ServerHttpRequest req = exchange.getRequest();

			String cache = (String) exchange.getAttribute("bodyCache");

			//Mono<String> mono = exchange.getRequest().getBody().collectList().map();

			ServerWebExchangeUtils.addOriginalRequestUrl(exchange, req.getURI());
			ServerHttpRequest request = req.mutate().build();

			DataBuffer bodyDataBuffer = stringBuffer(cache);
			final Flux<DataBuffer> bodyFlux = Flux.just(bodyDataBuffer);
			ServerHttpRequestDecorator serverHttpRequestDecorator = new ServerHttpRequestDecorator(request) {
				public Flux<DataBuffer> getBody() {
					return bodyFlux;
				}
			};

			return chain.filter(exchange.mutate().request((ServerHttpRequest) serverHttpRequestDecorator).build());
		};
	}

	private DataBuffer stringBuffer(final String value) {
		final byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
		final NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
		final DataBuffer buffer = (DataBuffer) nettyDataBufferFactory.allocateBuffer(bytes.length);
		buffer.write(bytes);
		return buffer;
	}

	private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
		Flux<DataBuffer> body = serverHttpRequest.getBody();
		AtomicReference<String> bodyRef = new AtomicReference<>();
		body.subscribe(buffer -> {
			CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
			DataBufferUtils.release(buffer);
			bodyRef.set(charBuffer.toString());
		});

		return bodyRef.get();
	}

	static {
		log = LoggerFactory.getLogger((Class) SpellGatewayFilterFactory.class);
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
