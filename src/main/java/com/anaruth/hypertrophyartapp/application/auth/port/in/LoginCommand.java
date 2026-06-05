package com.anaruth.hypertrophyartapp.application.auth.port.in;

public record LoginCommand(
        String email,
        String password
) {
}