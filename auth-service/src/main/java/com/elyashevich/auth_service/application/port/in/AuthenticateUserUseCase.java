package com.elyashevich.auth_service.application.port.in;

import com.elyashevich.auth_service.application.domain.model.JwtToken;
import com.elyashevich.auth_service.application.domain.model.User;

public interface AuthenticateUserUseCase {

    JwtToken authenticate(User user);
}
