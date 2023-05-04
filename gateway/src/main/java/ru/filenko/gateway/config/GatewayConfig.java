package ru.filenko.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.filenko.gateway.security.ForwardedCookieFilter;

@Configuration
public class GatewayConfig {
    private final String SERVICE_DATA = "service";
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("service-route", r -> r.path("/"+SERVICE_DATA+"**")
                        .filters(f -> f.rewritePath("/"+SERVICE_DATA+"/(?<path>.*)", "/${path}")
                                .filter(new ForwardedCookieFilter()))
                        .uri("lb://"+SERVICE_DATA))
                .build();
    }


}