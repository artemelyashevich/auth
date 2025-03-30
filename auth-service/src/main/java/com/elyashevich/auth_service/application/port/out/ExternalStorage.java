package com.elyashevich.auth_service.application.port.out;

import com.elyashevich.auth_service.application.domain.model.User;

public interface ExternalStorage {

    User findByEmail(String email);

    User save(User user);
}
