package com.anaruth.hypertrophyartapp.application.wellness.service;

import com.anaruth.hypertrophyartapp.application.user.port.out.UserRepository;
import com.anaruth.hypertrophyartapp.application.wellness.port.in.RegisterWellnessCheckInCommand;
import com.anaruth.hypertrophyartapp.application.wellness.port.in.RegisterWellnessCheckInResult;
import com.anaruth.hypertrophyartapp.application.wellness.port.in.RegisterWellnessCheckInUseCase;
import com.anaruth.hypertrophyartapp.application.wellness.port.out.WellnessCheckInRepository;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import com.anaruth.hypertrophyartapp.domain.wellness.model.WellnessCheckIn;
import org.springframework.stereotype.Service;

@Service
public class RegisterWellnessCheckInService implements RegisterWellnessCheckInUseCase {

    private final WellnessCheckInRepository wellnessCheckInRepository;
    private final UserRepository userRepository;

    public RegisterWellnessCheckInService(
            WellnessCheckInRepository wellnessCheckInRepository,
            UserRepository userRepository
    ) {
        this.wellnessCheckInRepository = wellnessCheckInRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RegisterWellnessCheckInResult register(RegisterWellnessCheckInCommand command) {
        UserId userId = UserId.from(command.userId());

        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        WellnessCheckIn wellnessCheckIn = WellnessCheckIn.create(
                userId,
                command.date(),
                command.physicalState(),
                command.mentalState(),
                command.emotionalState(),
                command.stressLevel(),
                command.motivationLevel(),
                command.notes()
        );

        WellnessCheckIn saved = wellnessCheckInRepository.save(wellnessCheckIn);

        return new RegisterWellnessCheckInResult(
                saved.id().value(),
                saved.userId().value(),
                saved.date(),
                saved.physicalState(),
                saved.mentalState(),
                saved.emotionalState(),
                saved.stressLevel(),
                saved.motivationLevel(),
                saved.notes()
        );
    }
}