package com.elyashevich.auth_service.adapter.in.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record AuthDto(

    @NotNull(message = "Email must be not null")
    @NotBlank(message = "Email must be not blank")
    @Email(message = "Invalid email format")
    String email,

    @NotNull(message = "Password must be not null")
    @NotBlank(message = "Password must be not blank")
    @Length(min = 8, max = 255, message = "Password must be in {min} and {max}")
    String password,

    List<Role> roles
) {
}
