package com.elyashevich.auth_service.application.port.in;

import com.elyashevich.auth_service.application.domain.model.JwtToken;

public interface RefreshTokenUseCase {

    JwtToken refreshToken(String refreshToken);
}
