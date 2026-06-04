package com.anaruth.hypertrophyartapp.application.user.port.in;

import com.anaruth.hypertrophyartapp.domain.auth.model.Role;
import com.anaruth.hypertrophyartapp.domain.user.model.UserMode;

import java.util.UUID;

public record CurrentUserProfileResult(
        UUID id,
        String name,
        String email,
        Role role,
        UserMode mode
) {
}