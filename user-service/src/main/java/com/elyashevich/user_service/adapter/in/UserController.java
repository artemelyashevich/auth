package com.elyashevich.user_service.adapter.in;

import com.elyashevich.user_service.adapter.in.dto.UserDto;
import com.elyashevich.user_service.adapter.in.dto.UserResponseDto;
import com.elyashevich.user_service.application.port.in.CreateUserCase;
import com.elyashevich.user_service.application.port.in.FindAllUsersUseCase;
import com.elyashevich.user_service.application.port.in.FindUserByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final FindUserByIdUseCase findUserByIdUseCase;
    private final CreateUserCase createUserCase;
    private final FindAllUsersUseCase findAllUsersUseCase;
    private final UserDtoMapper userDtoMapper;

    @GetMapping
    public List<UserResponseDto> findAll(){
        var users = this.findAllUsersUseCase.findAll();
        return users.stream()
            .map(userDtoMapper::toResponseDto)
            .toList();
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable("id") Long id){
        var user = this.findUserByIdUseCase.findUserById(id);
        return userDtoMapper.toResponseDto(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto create(@RequestBody UserDto userDto) {
        var candidate = this.userDtoMapper.toDomainModel(userDto);
        var user = this.createUserCase.save(candidate);
        return userDtoMapper.toResponseDto(user);
    }
}
