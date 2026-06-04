package com.anaruth.hypertrophyartapp.infrastructure.security;

import com.anaruth.hypertrophyartapp.domain.auth.model.Role;

import java.util.UUID;

public record AuthenticatedAccount(
        UUID id,
        String email,
        Role role
) {
}