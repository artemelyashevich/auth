package com.elyashevich.user_service.adapter.in.dto;

import com.elyashevich.user_service.application.domain.model.Role;

import java.util.List;

public record UserResponseDto(
    Long id,
    String email,
    List<Role> roles
) {
}
