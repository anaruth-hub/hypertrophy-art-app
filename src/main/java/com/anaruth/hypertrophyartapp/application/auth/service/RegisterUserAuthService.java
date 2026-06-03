package com.anaruth.hypertrophyartapp.application.auth.service;

import com.anaruth.hypertrophyartapp.application.auth.port.in.AuthResult;
import com.anaruth.hypertrophyartapp.application.auth.port.in.RegisterUserAuthCommand;
import com.anaruth.hypertrophyartapp.application.auth.port.in.RegisterUserAuthUseCase;
import com.anaruth.hypertrophyartapp.application.user.port.out.UserRepository;
import com.anaruth.hypertrophyartapp.domain.user.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserAuthService implements RegisterUserAuthUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserAuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResult registerUser(RegisterUserAuthCommand command) {
        userRepository.findByEmail(command.email())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("User email already exists");
                });

        String passwordHash = passwordEncoder.encode(command.password());

        User user = User.create(
                command.name(),
                command.email(),
                passwordHash,
                command.mode()
        );

        User savedUser = userRepository.save(user);

        return new AuthResult(
                "TEMP_TOKEN",
                savedUser.role(),
                savedUser.id().value(),
                savedUser.name(),
                savedUser.email()
        );
    }
}