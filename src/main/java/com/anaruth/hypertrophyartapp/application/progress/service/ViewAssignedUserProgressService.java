package com.anaruth.hypertrophyartapp.application.progress.service;

import com.anaruth.hypertrophyartapp.application.progress.port.in.ProgressSummaryResult;
import com.anaruth.hypertrophyartapp.application.progress.port.in.ViewAssignedUserProgressUseCase;
import com.anaruth.hypertrophyartapp.application.progress.port.in.ViewProgressSummaryUseCase;
import com.anaruth.hypertrophyartapp.application.user.port.out.UserRepository;
import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;
import com.anaruth.hypertrophyartapp.domain.user.model.User;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ViewAssignedUserProgressService implements ViewAssignedUserProgressUseCase {

    private final UserRepository userRepository;
    private final ViewProgressSummaryUseCase viewProgressSummaryUseCase;

    public ViewAssignedUserProgressService(
            UserRepository userRepository,
            ViewProgressSummaryUseCase viewProgressSummaryUseCase
    ) {
        this.userRepository = userRepository;
        this.viewProgressSummaryUseCase = viewProgressSummaryUseCase;
    }

    @Override
    public ProgressSummaryResult viewAssignedUserProgress(
            UUID authenticatedTrainerId,
            UUID userId
    ) {
        User user = userRepository.findById(UserId.from(userId))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.trainerId() == null ||
                !user.trainerId().equals(TrainerId.from(authenticatedTrainerId))) {
            throw new IllegalArgumentException("User is not assigned to this trainer");
        }

        return viewProgressSummaryUseCase.viewByUserId(userId);
    }
}