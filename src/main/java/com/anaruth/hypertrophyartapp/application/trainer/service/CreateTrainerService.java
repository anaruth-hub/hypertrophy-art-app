package com.anaruth.hypertrophyartapp.application.trainer.service;

import com.anaruth.hypertrophyartapp.application.trainer.port.in.CreateTrainerCommand;
import com.anaruth.hypertrophyartapp.application.trainer.port.in.CreateTrainerResult;
import com.anaruth.hypertrophyartapp.application.trainer.port.in.CreateTrainerUseCase;
import com.anaruth.hypertrophyartapp.application.trainer.port.out.TrainerRepository;
import com.anaruth.hypertrophyartapp.domain.trainer.model.Trainer;
import org.springframework.stereotype.Service;

@Service
public class CreateTrainerService implements CreateTrainerUseCase {

    private final TrainerRepository trainerRepository;

    public CreateTrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public CreateTrainerResult createTrainer(CreateTrainerCommand command) {
        Trainer trainer = Trainer.create(command.name(), command.email());

        Trainer savedTrainer = trainerRepository.save(trainer);

        return new CreateTrainerResult(
                savedTrainer.id().value(),
                savedTrainer.name(),
                savedTrainer.email()
        );
    }
}