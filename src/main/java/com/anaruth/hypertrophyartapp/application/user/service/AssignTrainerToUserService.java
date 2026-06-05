package com.anaruth.hypertrophyartapp.application.user.service;

import com.anaruth.hypertrophyartapp.application.trainer.port.out.TrainerRepository;
import com.anaruth.hypertrophyartapp.application.user.port.in.AssignTrainerToUserCommand;
import com.anaruth.hypertrophyartapp.application.user.port.in.AssignTrainerToUserResult;
import com.anaruth.hypertrophyartapp.application.user.port.in.AssignTrainerToUserUseCase;
import com.anaruth.hypertrophyartapp.application.user.port.out.UserRepository;
import com.anaruth.hypertrophyartapp.domain.trainer.model.Trainer;
import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;
import com.anaruth.hypertrophyartapp.domain.user.model.User;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import org.springframework.stereotype.Service;

@Service
public class AssignTrainerToUserService implements AssignTrainerToUserUseCase {

    private final UserRepository userRepository;
    private final TrainerRepository trainerRepository;

    public AssignTrainerToUserService(
            UserRepository userRepository,
            TrainerRepository trainerRepository
    ) {
        this.userRepository = userRepository;
        this.trainerRepository = trainerRepository;
    }

    @Override
    public AssignTrainerToUserResult assignTrainer(AssignTrainerToUserCommand command) {
        UserId userId = UserId.from(command.userId());
        TrainerId trainerId = TrainerId.from(command.trainerId());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new IllegalArgumentException("Trainer not found"));

        user.assignTrainer(trainer.id());
        User savedUser = userRepository.save(user);

        return new AssignTrainerToUserResult(
                savedUser.id().value(),
                savedUser.name(),
                savedUser.mode(),
                savedUser.trainerId().value()
        );
    }
}