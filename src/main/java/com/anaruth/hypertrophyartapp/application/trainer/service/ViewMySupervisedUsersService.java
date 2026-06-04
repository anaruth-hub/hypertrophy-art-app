package com.anaruth.hypertrophyartapp.application.trainer.service;

import com.anaruth.hypertrophyartapp.application.trainer.port.in.SupervisedUserResult;
import com.anaruth.hypertrophyartapp.application.trainer.port.in.ViewMySupervisedUsersUseCase;
import com.anaruth.hypertrophyartapp.application.user.port.out.UserRepository;
import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ViewMySupervisedUsersService implements ViewMySupervisedUsersUseCase {

    private final UserRepository userRepository;

    public ViewMySupervisedUsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<SupervisedUserResult> viewMySupervisedUsers(UUID authenticatedTrainerId) {
        return userRepository.findByTrainerId(TrainerId.from(authenticatedTrainerId))
                .stream()
                .map(user -> new SupervisedUserResult(
                        user.id().value(),
                        user.name(),
                        user.email(),
                        user.mode()
                ))
                .toList();
    }
}