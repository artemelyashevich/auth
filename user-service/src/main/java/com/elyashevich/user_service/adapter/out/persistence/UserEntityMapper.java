package com.elyashevich.user_service.adapter.out.persistence;

import com.elyashevich.user_service.application.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

    public User toUser(UserEntity userEntity){
        return new User(userEntity.getId(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getRoles());
    }

    public UserEntity toUserEntity(User user){
        return UserEntity.builder()
            .email(user.email())
            .password(user.password())
            .build();
    }
}
