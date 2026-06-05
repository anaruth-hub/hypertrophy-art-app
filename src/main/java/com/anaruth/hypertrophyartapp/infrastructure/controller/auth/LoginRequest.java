package com.anaruth.hypertrophyartapp.infrastructure.controller.auth;

public record LoginRequest(
        String email,
        String password
) {
}