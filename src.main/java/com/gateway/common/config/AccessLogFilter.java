package com.gateway.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: zqz
 * @CreateTime: 2024/05/28
 * @Description: 过滤器
 * @Version: 1.0
 */
@Component
public class AccessLogFilter implements GlobalFilter, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(AccessLogFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        logger.info("method: {}, uri: {}, queryparams: {}", new Object[]{serverHttpRequest.getMethod().name(), serverHttpRequest.getURI(), serverHttpRequest.getQueryParams()});
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -10;
    }
}
