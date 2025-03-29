package com.elyashevich.user_service.application.domain.model;

public record User(
    Long id,
    String email,
    String password
) {
}
