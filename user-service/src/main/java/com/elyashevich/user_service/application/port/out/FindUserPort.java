package com.elyashevich.user_service.application.port.out;

import com.elyashevich.user_service.application.domain.model.User;

public interface FindUserPort {

    User find(Long id);
}
