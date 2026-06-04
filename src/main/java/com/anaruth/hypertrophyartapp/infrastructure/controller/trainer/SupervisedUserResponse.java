package com.anaruth.hypertrophyartapp.infrastructure.controller.trainer;

import com.anaruth.hypertrophyartapp.domain.user.model.UserMode;

import java.util.UUID;

public record SupervisedUserResponse(
        UUID id,
        String name,
        String email,
        UserMode mode
) {
}