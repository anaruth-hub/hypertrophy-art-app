package com.anaruth.hypertrophyartapp.application.user.port.in;

import com.anaruth.hypertrophyartapp.domain.user.model.UserMode;

import java.util.UUID;

public record CreateUserResult(
        UUID id,
        String name,
        String email,
        UserMode mode
) {
}