package com.anaruth.hypertrophyartapp.infrastructure.controller.trainer;

import java.util.UUID;

public record TrainerResponse(
        UUID id,
        String name,
        String email
) {
}