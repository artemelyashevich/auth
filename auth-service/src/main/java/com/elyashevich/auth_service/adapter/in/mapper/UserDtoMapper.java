package com.elyashevich.auth_service.adapter.in.mapper;

import com.elyashevich.auth_service.adapter.in.dto.AuthDto;
import com.elyashevich.auth_service.adapter.in.dto.Role;
import com.elyashevich.auth_service.application.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDtoMapper {

    public User toUser(AuthDto userDto) {
        return new User(
            null,
            userDto.email(),
            userDto.password(),
            List.of(Role.ROLE_USER)
        );
    }
}
