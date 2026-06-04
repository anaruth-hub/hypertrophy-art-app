package com.anaruth.hypertrophyartapp.application.user.service;

import com.anaruth.hypertrophyartapp.application.user.port.in.CurrentUserProfileResult;
import com.anaruth.hypertrophyartapp.application.user.port.in.GetCurrentUserProfileUseCase;
import com.anaruth.hypertrophyartapp.application.user.port.out.UserRepository;
import com.anaruth.hypertrophyartapp.domain.user.model.User;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetCurrentUserProfileService implements GetCurrentUserProfileUseCase {

    private final UserRepository userRepository;

    public GetCurrentUserProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CurrentUserProfileResult getCurrentUserProfile(UUID authenticatedUserId) {
        User user = userRepository.findById(UserId.from(authenticatedUserId))
                .orElseThrow(() -> new IllegalArgumentException("Authenticated user not found"));

        return new CurrentUserProfileResult(
                user.id().value(),
                user.name(),
                user.email(),
                user.role(),
                user.mode()
        );
    }
}