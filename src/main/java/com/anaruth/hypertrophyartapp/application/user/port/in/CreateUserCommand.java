package com.anaruth.hypertrophyartapp.application.user.port.in;

import com.anaruth.hypertrophyartapp.domain.user.model.UserMode;

public record CreateUserCommand(
        String name,
        String email,
        UserMode mode
) {
}