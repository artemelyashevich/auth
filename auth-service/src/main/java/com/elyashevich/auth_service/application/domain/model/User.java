package com.elyashevich.auth_service.application.domain.model;

import com.elyashevich.auth_service.adapter.in.dto.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    private String email;

    private String password;

    private List<Role> roles;
}
