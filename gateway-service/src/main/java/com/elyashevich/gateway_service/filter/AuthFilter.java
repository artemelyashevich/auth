package com.elyashevich.gateway_service.filter;

import com.elyashevich.gateway_service.api.http.rest.AuthRestClient;
import com.elyashevich.gateway_service.validator.RouteValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final AuthRestClient authRestClient;

    public static class Config{}

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!RouteValidator.isSecured.test(exchange.getRequest())) {
                return chain.filter(exchange);
            }
            var authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (authHeader == null) {
                return handleInvalidAccess(HttpStatus.UNAUTHORIZED, exchange);
            }
            var token = authHeader.substring(7);
            return validateToken(exchange, chain, token);
        };
    }

    private Mono<Void> validateToken(ServerWebExchange exchange, GatewayFilterChain chain, String token) {
        var tokenResponse = this.authRestClient.validate(token);
        if (tokenResponse == null){
            return handleInvalidAccess(HttpStatus.UNAUTHORIZED, exchange);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> handleInvalidAccess(HttpStatus status, ServerWebExchange exchange) {
        var response = exchange.getResponse();
        response.setStatusCode(status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        var res = Mono.just(response.bufferFactory().wrap("Invalid or missing JWT token".getBytes()));
        return response.writeWith(res);
    }
}
