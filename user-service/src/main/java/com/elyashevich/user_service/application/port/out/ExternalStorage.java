package com.elyashevich.user_service.application.port.out;

import com.elyashevich.user_service.application.domain.model.User;

import java.util.List;

public interface ExternalStorage {

    User save(User user);

    User find(Long id);

    List<User> findAll();
}
