package com.anaruth.hypertrophyartapp.application.trainer.service;

import com.anaruth.hypertrophyartapp.application.trainer.port.in.CreateTrainerResult;
import com.anaruth.hypertrophyartapp.application.trainer.port.in.ViewAllTrainersUseCase;
import com.anaruth.hypertrophyartapp.application.trainer.port.out.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewAllTrainersService implements ViewAllTrainersUseCase {

    private final TrainerRepository trainerRepository;

    public ViewAllTrainersService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public List<CreateTrainerResult> viewAll() {
        return trainerRepository.findAll()
                .stream()
                .map(trainer -> new CreateTrainerResult(
                        trainer.id().value(),
                        trainer.name(),
                        trainer.email()
                ))
                .toList();
    }
}