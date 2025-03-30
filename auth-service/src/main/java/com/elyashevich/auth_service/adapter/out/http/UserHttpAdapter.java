package com.elyashevich.auth_service.adapter.out.http;

import com.elyashevich.auth_service.adapter.in.mapper.UserDtoMapper;
import com.elyashevich.auth_service.application.domain.model.User;
import com.elyashevich.auth_service.application.port.out.ExternalStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserHttpAdapter implements ExternalStorage {

    private final UserRestClient userRestClient;
    private final UserDtoMapper userDtoMapper;

    @Override
    public User findByEmail(String email) {
        return this.userDtoMapper.toUser(this.userRestClient.findByEmail(email));
    }

    @Override
    public User save(User user) {
        return this.userDtoMapper.toUser(this.userRestClient.save(user));
    }
}
