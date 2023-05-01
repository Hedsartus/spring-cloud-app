package ru.filenko.gateway.security;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class ForwardedCookieFilter implements GatewayFilter {
    private final String HEADER_VAL_REQUEST = "Cookie";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        headers.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(HEADER_VAL_REQUEST))
                .forEach(entry -> exchange.getRequest().mutate()
                        .header(HttpHeaders.AUTHORIZATION, entry.getValue().get(0))
                        .build());

        return chain.filter(exchange);
    }
}