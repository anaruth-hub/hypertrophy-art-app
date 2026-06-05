package com.anaruth.hypertrophyartapp.application.trainer.port.in;

import com.anaruth.hypertrophyartapp.domain.user.model.UserMode;

import java.util.UUID;

public record SupervisedUserResult(
        UUID id,
        String name,
        String email,
        UserMode mode
) {
}