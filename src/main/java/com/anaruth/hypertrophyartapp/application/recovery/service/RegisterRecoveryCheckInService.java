package com.anaruth.hypertrophyartapp.application.recovery.service;

import com.anaruth.hypertrophyartapp.application.recovery.port.in.RegisterRecoveryCheckInCommand;
import com.anaruth.hypertrophyartapp.application.recovery.port.in.RegisterRecoveryCheckInResult;
import com.anaruth.hypertrophyartapp.application.recovery.port.in.RegisterRecoveryCheckInUseCase;
import com.anaruth.hypertrophyartapp.application.recovery.port.out.RecoveryCheckInRepository;
import com.anaruth.hypertrophyartapp.application.user.port.out.UserRepository;
import com.anaruth.hypertrophyartapp.domain.recovery.model.RecoveryCheckIn;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import org.springframework.stereotype.Service;

@Service
public class RegisterRecoveryCheckInService implements RegisterRecoveryCheckInUseCase {

    private final RecoveryCheckInRepository recoveryCheckInRepository;
    private final UserRepository userRepository;

    public RegisterRecoveryCheckInService(
            RecoveryCheckInRepository recoveryCheckInRepository,
            UserRepository userRepository
    ) {
        this.recoveryCheckInRepository = recoveryCheckInRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RegisterRecoveryCheckInResult register(RegisterRecoveryCheckInCommand command) {
        UserId userId = UserId.from(command.userId());

        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        RecoveryCheckIn recoveryCheckIn = RecoveryCheckIn.create(
                userId,
                command.date(),
                command.fatigueLevel(),
                command.sorenessLevel(),
                command.energyLevel(),
                command.sleepHours(),
                command.notes()
        );

        RecoveryCheckIn saved = recoveryCheckInRepository.save(recoveryCheckIn);

        return new RegisterRecoveryCheckInResult(
                saved.id().value(),
                saved.userId().value(),
                saved.date(),
                saved.fatigueLevel(),
                saved.sorenessLevel(),
                saved.energyLevel(),
                saved.sleepHours(),
                saved.notes()
        );
    }
}