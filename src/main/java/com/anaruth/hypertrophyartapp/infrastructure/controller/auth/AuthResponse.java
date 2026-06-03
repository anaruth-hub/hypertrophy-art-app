package com.anaruth.hypertrophyartapp.infrastructure.controller.auth;

import com.anaruth.hypertrophyartapp.domain.auth.model.Role;

import java.util.UUID;

public record AuthResponse(
        String token,
        Role role,
        UUID id,
        String name,
        String email
) {
}