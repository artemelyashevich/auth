package com.elyashevich.user_service.adapter.out.persistence;

import com.elyashevich.user_service.application.domain.exception.ResourceNotFoundException;
import com.elyashevich.user_service.application.domain.model.User;
import com.elyashevich.user_service.application.port.out.ExternalStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPersistenceAdapter implements ExternalStorage {

    private static final String USER_WITH_ID_WAS_NOT_FOUND_TEMPLATE = "User with id '%s' was not found";
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User save(User user) {
        var userEntity = this.userRepository.save(this.userEntityMapper.toUserEntity(user));
        return this.userEntityMapper.toUser(userEntity);
    }

    @Override
    public User find(Long id) {
        var userEntity = this.userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(USER_WITH_ID_WAS_NOT_FOUND_TEMPLATE.formatted(id))
        );
        return this.userEntityMapper.toUser(userEntity);
    }

    @Override
    public List<User> findAll() {
        var users = this.userRepository.findAll();
        return users.stream()
            .map(this.userEntityMapper::toUser)
            .toList();
    }
}
