package com.anaruth.hypertrophyartapp.application.user.port.in;

public interface CreateUserUseCase {

    CreateUserResult createUser(CreateUserCommand command);
}