package com.anaruth.hypertrophyartapp.infrastructure.controller.auth;

public record RegisterTrainerRequest(
        String name,
        String email,
        String password
) {
}