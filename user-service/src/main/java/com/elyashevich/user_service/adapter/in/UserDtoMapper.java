package com.elyashevich.user_service.adapter.in;

import com.elyashevich.user_service.adapter.in.dto.UserDto;
import com.elyashevich.user_service.adapter.in.dto.UserResponseDto;
import com.elyashevich.user_service.adapter.out.persistence.UserEntity;
import com.elyashevich.user_service.application.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public User toDomainModel(UserDto dto) {
        return new User(
            dto.id(),
            dto.email(),
            dto.password()
        );
    }

    public UserResponseDto toResponseDto(User user) {
        return new UserResponseDto(user.id(), user.email());
    }
}
