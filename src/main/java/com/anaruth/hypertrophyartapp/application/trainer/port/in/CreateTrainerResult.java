package com.anaruth.hypertrophyartapp.application.trainer.port.in;

import java.util.UUID;

public record CreateTrainerResult(
        UUID id,
        String name,
        String email
) {
}