package com.anaruth.hypertrophyartapp.application.auth.port.in;

public interface RegisterTrainerAuthUseCase {

    AuthResult registerTrainer(RegisterTrainerAuthCommand command);
}