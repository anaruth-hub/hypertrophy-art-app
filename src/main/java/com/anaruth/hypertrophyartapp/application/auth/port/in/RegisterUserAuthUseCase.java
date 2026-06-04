package com.anaruth.hypertrophyartapp.application.auth.port.in;

public interface RegisterUserAuthUseCase {

    AuthResult registerUser(RegisterUserAuthCommand command);
}