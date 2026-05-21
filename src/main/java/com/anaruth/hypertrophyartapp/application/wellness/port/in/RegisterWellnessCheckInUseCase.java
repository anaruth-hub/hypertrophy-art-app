package com.anaruth.hypertrophyartapp.application.wellness.port.in;

public interface RegisterWellnessCheckInUseCase {

    RegisterWellnessCheckInResult register(RegisterWellnessCheckInCommand command);
}