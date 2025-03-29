package com.elyashevich.user_service.application.port.in;

import com.elyashevich.user_service.application.domain.model.User;

import java.util.List;

public interface FindAllUsersUseCase {

    List<User> findAll();
}
