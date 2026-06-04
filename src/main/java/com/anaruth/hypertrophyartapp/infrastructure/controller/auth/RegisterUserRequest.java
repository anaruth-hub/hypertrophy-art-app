package com.anaruth.hypertrophyartapp.infrastructure.controller.auth;

import com.anaruth.hypertrophyartapp.domain.user.model.UserMode;

public record RegisterUserRequest(
        String name,
        String email,
        String password,
        UserMode mode
) {
}