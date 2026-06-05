package com.anaruth.hypertrophyartapp.application.training.service;

import com.anaruth.hypertrophyartapp.application.training.port.in.RegisterTrainingCommand;
import com.anaruth.hypertrophyartapp.application.training.port.in.RegisterTrainingResult;
import com.anaruth.hypertrophyartapp.application.training.port.in.RegisterTrainingUseCase;
import com.anaruth.hypertrophyartapp.application.training.port.out.TrainingRepository;
import com.anaruth.hypertrophyartapp.application.user.port.out.UserRepository;
import com.anaruth.hypertrophyartapp.domain.training.model.Training;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import org.springframework.stereotype.Service;

@Service
public class RegisterTrainingService implements RegisterTrainingUseCase {

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;

    public RegisterTrainingService(
            TrainingRepository trainingRepository,
            UserRepository userRepository
    ) {
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RegisterTrainingResult registerTraining(RegisterTrainingCommand command) {
        UserId userId = UserId.from(command.userId());

        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Training training = Training.create(
                userId,
                command.date(),
                command.muscleGroup(),
                command.exercises(),
                command.intensity(),
                command.durationMinutes()
        );

        Training savedTraining = trainingRepository.save(training);

        return new RegisterTrainingResult(
                savedTraining.id().value(),
                savedTraining.userId().value(),
                savedTraining.date(),
                savedTraining.muscleGroup(),
                savedTraining.exercises(),
                savedTraining.intensity(),
                savedTraining.durationMinutes()
        );
    }
}