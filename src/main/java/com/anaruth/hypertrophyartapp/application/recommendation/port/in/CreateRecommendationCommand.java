package com.anaruth.hypertrophyartapp.application.recommendation.port.in;

import java.time.LocalDate;
import java.util.UUID;

public record CreateRecommendationCommand(
        UUID authenticatedTrainerId,
        UUID userId,
        LocalDate date,
        String message
) {
}