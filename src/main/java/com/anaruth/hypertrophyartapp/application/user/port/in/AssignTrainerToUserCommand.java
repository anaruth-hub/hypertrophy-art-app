package com.anaruth.hypertrophyartapp.application.user.port.in;

import java.util.UUID;

public record AssignTrainerToUserCommand(
        UUID userId,
        UUID trainerId
) {
}