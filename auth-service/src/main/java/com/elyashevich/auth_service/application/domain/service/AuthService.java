package com.elyashevich.auth_service.application.domain.service;

import com.elyashevich.auth_service.application.domain.exception.PasswordMismatchException;
import com.elyashevich.auth_service.application.domain.model.JwtToken;
import com.elyashevich.auth_service.application.domain.model.User;
import com.elyashevich.auth_service.application.domain.util.JwtUtil;
import com.elyashevich.auth_service.application.port.in.AuthenticateUserUseCase;
import com.elyashevich.auth_service.application.port.in.RefreshTokenUseCase;
import com.elyashevich.auth_service.application.port.in.RegisterUserUseCase;
import com.elyashevich.auth_service.application.port.in.ValidateTokenUseCase;
import com.elyashevich.auth_service.application.port.out.ExternalStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements
    AuthenticateUserUseCase,
    RegisterUserUseCase,
    ValidateTokenUseCase,
    RefreshTokenUseCase {

    private final PasswordEncoder passwordEncoder;
    private final ExternalStorage externalStorage;

    @Override
    public JwtToken authenticate(User user) {
        var candidate = this.externalStorage.findByEmail(user.getEmail());
        if (!this.passwordEncoder.matches(candidate.getPassword(), user.getPassword())) {
            throw new PasswordMismatchException("Password does not match");
        }
        return this.generateAuthResponse(candidate);
    }

    @Override
    public JwtToken register(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        var result = this.externalStorage.save(user);
        return this.generateAuthResponse(result);
    }

    @Override
    public String validate(String token) {
        return JwtUtil.validate(token);
    }

    // TODO
    @Override
    public JwtToken refreshToken(String refreshToken) {

        return null;
    }

    private JwtToken generateAuthResponse(User user){
        var accessToken = JwtUtil.generateJwtToken(user,1800000L);
        var refreshToken = JwtUtil.generateJwtToken(user,864000000L);
        return new JwtToken(accessToken, refreshToken);
    }
}
