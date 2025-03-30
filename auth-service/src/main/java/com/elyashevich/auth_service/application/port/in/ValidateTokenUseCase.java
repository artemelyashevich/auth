package com.elyashevich.auth_service.application.port.in;

import com.elyashevich.auth_service.application.domain.model.JwtToken;

public interface ValidateTokenUseCase {

    String validate(String token);
}
