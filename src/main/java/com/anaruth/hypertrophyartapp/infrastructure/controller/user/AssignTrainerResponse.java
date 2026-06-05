package com.anaruth.hypertrophyartapp.infrastructure.controller.user;

import com.anaruth.hypertrophyartapp.domain.user.model.UserMode;

import java.util.UUID;

public record AssignTrainerResponse(
        UUID userId,
        String userName,
        UserMode mode,
        UUID trainerId
) {
}