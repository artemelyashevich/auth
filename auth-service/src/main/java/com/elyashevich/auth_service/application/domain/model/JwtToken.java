package com.elyashevich.auth_service.application.domain.model;

public record JwtToken(
    String accessToken,
    String refreshToken
) {
}
