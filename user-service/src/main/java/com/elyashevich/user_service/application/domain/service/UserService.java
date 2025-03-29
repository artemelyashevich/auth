package com.elyashevich.user_service.application.domain.service;

import com.elyashevich.user_service.application.domain.model.User;
import com.elyashevich.user_service.application.port.in.CreateUserCase;
import com.elyashevich.user_service.application.port.in.FindAllUsersUseCase;
import com.elyashevich.user_service.application.port.in.FindUserByIdUseCase;
import com.elyashevich.user_service.application.port.out.ExternalStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements CreateUserCase, FindUserByIdUseCase, FindAllUsersUseCase {

    private final ExternalStorage storage;

    @Override
    public User save(User user) {
        return this.storage.save(user);
    }

    @Override
    public User findUserById(long id) {
        return this.storage.find(id);
    }

    @Override
    public List<User> findAll() {
        return this.storage.findAll();
    }
}
