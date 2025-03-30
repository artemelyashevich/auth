package com.elyashevich.user_service.adapter.in;

import com.elyashevich.user_service.adapter.in.dto.UserDto;
import com.elyashevich.user_service.adapter.in.dto.UserResponseDto;
import com.elyashevich.user_service.application.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserDtoMapper {

    public User toDomainModel(UserDto dto) {
        return new User(
            dto.id(),
            dto.email(),
            dto.password(),
            Collections.emptyList()
        );
    }

    public UserResponseDto toResponseDto(User user) {
        return new UserResponseDto(user.id(), user.email(), user.roles());
    }
}
