package com.anaruth.hypertrophyartapp.application.auth.service;

import com.anaruth.hypertrophyartapp.application.auth.port.in.AuthResult;
import com.anaruth.hypertrophyartapp.application.auth.port.in.RegisterTrainerAuthCommand;
import com.anaruth.hypertrophyartapp.application.auth.port.in.RegisterTrainerAuthUseCase;
import com.anaruth.hypertrophyartapp.application.trainer.port.out.TrainerRepository;
import com.anaruth.hypertrophyartapp.domain.trainer.model.Trainer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterTrainerAuthService implements RegisterTrainerAuthUseCase {

    private final TrainerRepository trainerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public RegisterTrainerAuthService(
            TrainerRepository trainerRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.trainerRepository = trainerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResult registerTrainer(RegisterTrainerAuthCommand command) {
        trainerRepository.findByEmail(command.email())
                .ifPresent(trainer -> {
                    throw new IllegalArgumentException("Trainer email already exists");
                });

        String passwordHash = passwordEncoder.encode(command.password());

        Trainer trainer = Trainer.create(
                command.name(),
                command.email(),
                passwordHash
        );

        Trainer savedTrainer = trainerRepository.save(trainer);

        String token = jwtService.generateToken(
                savedTrainer.id().value(),
                savedTrainer.email(),
                savedTrainer.role()
        );

        return new AuthResult(
                token,
                savedTrainer.role(),
                savedTrainer.id().value(),
                savedTrainer.name(),
                savedTrainer.email()
        );
    }
}