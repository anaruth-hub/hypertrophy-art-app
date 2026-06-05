package com.anaruth.hypertrophyartapp.application.auth.port.in;

public interface LoginUseCase {

    AuthResult login(LoginCommand command);
}