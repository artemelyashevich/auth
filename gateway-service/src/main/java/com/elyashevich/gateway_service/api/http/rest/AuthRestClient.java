package com.elyashevich.gateway_service.api.http.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface AuthRestClient {

    @GetMapping("/api/v1/auth/{token}")
    String validate(final @PathVariable("token") String email);
}