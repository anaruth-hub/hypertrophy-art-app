package com.anaruth.hypertrophyartapp.application.auth.port.in;

import com.anaruth.hypertrophyartapp.domain.auth.model.Role;

import java.util.UUID;

public record AuthResult(
        String token,
        Role role,
        UUID id,
        String name,
        String email
) {
}