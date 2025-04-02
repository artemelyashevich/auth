package com.elyashevich.gateway_service.validator;

import lombok.experimental.UtilityClass;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;
import java.util.function.Predicate;

@UtilityClass
public class RouteValidator {

    private static final List<String> ENDPOINTS = List.of(
        "/ap1/v1/auth/*"
    );

    public static final Predicate<ServerHttpRequest> isSecured = request ->
        ENDPOINTS.stream()
            .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
