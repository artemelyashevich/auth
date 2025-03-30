package com.elyashevich.auth_service.adapter.out.http;

import com.elyashevich.auth_service.adapter.in.dto.AuthDto;
import com.elyashevich.auth_service.application.domain.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserRestClient {

    @GetMapping("/api/v1/users/{email}")
    AuthDto findByEmail(final @PathVariable("email") String email);

    @PostMapping("/api/v1/users")
    AuthDto save(final @RequestBody User user);
}