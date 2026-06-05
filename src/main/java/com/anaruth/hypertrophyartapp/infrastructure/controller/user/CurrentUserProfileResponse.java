package com.anaruth.hypertrophyartapp.infrastructure.controller.user;

import com.anaruth.hypertrophyartapp.domain.auth.model.Role;
import com.anaruth.hypertrophyartapp.domain.user.model.UserMode;

import java.util.UUID;

public record CurrentUserProfileResponse(
        UUID id,
        String name,
        String email,
        Role role,
        UserMode mode
) {
}