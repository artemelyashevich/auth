package com.elyashevich.user_service.application.port.out;

import com.elyashevich.user_service.application.domain.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FindUsersPort {

    List<User> findAll(Pageable pageable);
}
