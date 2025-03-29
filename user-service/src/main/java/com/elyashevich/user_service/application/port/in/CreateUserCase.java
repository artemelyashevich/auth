package com.elyashevich.user_service.application.port.in;

import com.elyashevich.user_service.application.domain.model.User;

public interface CreateUserCase {

    User save(User user);
}
