package com.anaruth.hypertrophyartapp.application.user.service;

import com.anaruth.hypertrophyartapp.application.user.port.in.CreateUserCommand;
import com.anaruth.hypertrophyartapp.application.user.port.in.CreateUserResult;
import com.anaruth.hypertrophyartapp.application.user.port.in.CreateUserUseCase;
import com.anaruth.hypertrophyartapp.application.user.port.out.UserRepository;
import com.anaruth.hypertrophyartapp.domain.user.model.User;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService implements CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CreateUserResult createUser(CreateUserCommand command) {
        User user = User.create(
                command.name(),
                command.email(),
                "{legacy-password-hash}",
                command.mode()
        );

        User savedUser = userRepository.save(user);

        return new CreateUserResult(
                savedUser.id().value(),
                savedUser.name(),
                savedUser.email(),
                savedUser.mode()
        );
    }
}