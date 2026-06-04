package com.anaruth.hypertrophyartapp.application.auth.service;

import com.anaruth.hypertrophyartapp.application.auth.port.in.AuthResult;
import com.anaruth.hypertrophyartapp.application.auth.port.in.LoginCommand;
import com.anaruth.hypertrophyartapp.application.auth.port.in.LoginUseCase;
import com.anaruth.hypertrophyartapp.application.trainer.port.out.TrainerRepository;
import com.anaruth.hypertrophyartapp.application.user.port.out.UserRepository;
import com.anaruth.hypertrophyartapp.domain.trainer.model.Trainer;
import com.anaruth.hypertrophyartapp.domain.user.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginUseCase {

    private final UserRepository userRepository;
    private final TrainerRepository trainerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginService(
            UserRepository userRepository,
            TrainerRepository trainerRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.trainerRepository = trainerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResult login(LoginCommand command) {
        return userRepository.findByEmail(command.email())
                .map(user -> loginUser(user, command.password()))
                .orElseGet(() -> trainerRepository.findByEmail(command.email())
                        .map(trainer -> loginTrainer(trainer, command.password()))
                        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password")));
    }

    private AuthResult loginUser(User user, String rawPassword) {
        if (!passwordEncoder.matches(rawPassword, user.passwordHash())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = jwtService.generateToken(
                user.id().value(),
                user.email(),
                user.role()
        );

        return new AuthResult(
                token,
                user.role(),
                user.id().value(),
                user.name(),
                user.email()
        );
    }

    private AuthResult loginTrainer(Trainer trainer, String rawPassword) {
        if (!passwordEncoder.matches(rawPassword, trainer.passwordHash())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = jwtService.generateToken(
                trainer.id().value(),
                trainer.email(),
                trainer.role()
        );

        return new AuthResult(
                token,
                trainer.role(),
                trainer.id().value(),
                trainer.name(),
                trainer.email()
        );
    }
}