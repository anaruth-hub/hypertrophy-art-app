package com.anaruth.hypertrophyartapp.infrastructure.controller.user;

import com.anaruth.hypertrophyartapp.domain.user.model.UserMode;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        UserMode mode
) {
}