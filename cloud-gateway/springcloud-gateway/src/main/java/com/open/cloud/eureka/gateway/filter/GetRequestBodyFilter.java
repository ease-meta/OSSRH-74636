package com.open.cloud.eureka.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

public class GetRequestBodyFilter implements GlobalFilter, Ordered
{
    private static final Logger log;
    private static final String CACHE_REQUEST_BODY_OBJECT_KEY = "bodyCache";
    private static final List<HttpMessageReader<?>> messageReaders;
    
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {
        final Class inClass = String.class;
        final ServerRequest serverRequest = ServerRequest.create(exchange, (List)GetRequestBodyFilter.messageReaders);
        final ServerHttpRequest request = exchange.getRequest();
        final String schema = request.getURI().getScheme();
        if (!"http".equals(schema) && !"https".equals(schema)) {
            return (Mono<Void>)chain.filter(exchange);
        }
        final String contentType = request.getHeaders().getFirst("Content-Type");
        final String upload = request.getHeaders().getFirst("upload");
        if (contentType == null || contentType.length() == 0) {
            return (Mono<Void>)chain.filter(exchange);
        }
        if ("true".equals(upload)) {
            return (Mono<Void>)chain.filter(exchange);
        }
        final Mono<?> modifiedBody = (Mono<?>)serverRequest.bodyToMono(inClass).flatMap(o -> {
            GetRequestBodyFilter.log.debug("requestBody:{}", o);
            exchange.getAttributes().put("bodyCache", o);
            return Mono.justOrEmpty(o);
        });
        return (Mono<Void>)chain.filter(exchange.mutate().request(request).build());
    }
    
    public int getOrder() {
        return 0;
    }
    
    private String getURLDecoder(final String val) {
        try {
            return URLDecoder.decode(val, "utf-8");
        }
        catch (Exception e) {
            GetRequestBodyFilter.log.error("getURLDecoder error,{}", (Throwable)e);
            return val;
        }
    }
    
    ServerHttpRequestDecorator decorate(final ServerWebExchange exchange, final HttpHeaders headers, final CachedBodyOutputMessage outputMessage) {
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            public HttpHeaders getHeaders() {
                final long contentLength = headers.getContentLength();
                final HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll((Map)super.getHeaders());
                if (contentLength > 0L) {
                    httpHeaders.setContentLength(contentLength);
                }
                else {
                    httpHeaders.set("Transfer-Encoding", "chunked");
                }
                return httpHeaders;
            }
            
            public Flux<DataBuffer> getBody() {
                return (Flux<DataBuffer>)outputMessage.getBody();
            }
        };
    }
    
    static {
        log = LoggerFactory.getLogger((Class)GetRequestBodyFilter.class);
        messageReaders = HandlerStrategies.withDefaults().messageReaders();
    }
}
