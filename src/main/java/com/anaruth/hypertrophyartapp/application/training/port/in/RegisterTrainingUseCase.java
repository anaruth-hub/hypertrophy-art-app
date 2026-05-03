package com.anaruth.hypertrophyartapp.application.training.port.in;

public interface RegisterTrainingUseCase {

    RegisterTrainingResult registerTraining(RegisterTrainingCommand command);
}