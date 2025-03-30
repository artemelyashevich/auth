package com.elyashevich.auth_service.adapter.in.controller;

import com.elyashevich.auth_service.adapter.in.dto.AuthDto;
import com.elyashevich.auth_service.adapter.in.mapper.UserDtoMapper;
import com.elyashevich.auth_service.application.domain.model.JwtToken;
import com.elyashevich.auth_service.application.port.in.AuthenticateUserUseCase;
import com.elyashevich.auth_service.application.port.in.RegisterUserUseCase;
import com.elyashevich.auth_service.application.port.in.ValidateTokenUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final RegisterUserUseCase registerUserUseCase;
    private final ValidateTokenUseCase validateTokenUseCase;
    private final UserDtoMapper userDtoMapper;

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtToken authenticate(@RequestBody AuthDto authDto) {
        return this.authenticateUserUseCase.authenticate(this.userDtoMapper.toUser(authDto));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtToken register(@RequestBody AuthDto authDto) {
        return this.registerUserUseCase.register(this.userDtoMapper.toUser(authDto));
    }

    @GetMapping("/{token}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String validate(@PathVariable("token") String token) {
        this.validateTokenUseCase.validate(token);
        return token;
    }
}
