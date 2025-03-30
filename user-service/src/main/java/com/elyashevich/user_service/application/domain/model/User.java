package com.elyashevich.user_service.application.domain.model;

import java.util.List;

public record User(
    Long id,
    String email,
    String password,
    List<Role> roles
) {
}
