package com.anaruth.hypertrophyartapp.application.recovery.port.in;

public interface RegisterRecoveryCheckInUseCase {

    RegisterRecoveryCheckInResult register(RegisterRecoveryCheckInCommand command);
}