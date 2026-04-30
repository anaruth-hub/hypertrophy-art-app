package com.anaruth.hypertrophyartapp.application.trainer.port.in;

public interface CreateTrainerUseCase {

    CreateTrainerResult createTrainer(CreateTrainerCommand command);
}