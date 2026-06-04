package com.anaruth.hypertrophyartapp.application.auth.port.in;

public record RegisterTrainerAuthCommand(
        String name,
        String email,
        String password
) {
}