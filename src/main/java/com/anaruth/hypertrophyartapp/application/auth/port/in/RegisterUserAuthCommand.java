package com.anaruth.hypertrophyartapp.application.auth.port.in;

import com.anaruth.hypertrophyartapp.domain.user.model.UserMode;

public record RegisterUserAuthCommand(
        String name,
        String email,
        String password,
        UserMode mode
) {
}