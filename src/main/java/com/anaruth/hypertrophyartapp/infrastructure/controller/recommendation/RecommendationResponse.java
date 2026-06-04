package com.anaruth.hypertrophyartapp.infrastructure.controller.recommendation;

import java.time.LocalDate;
import java.util.UUID;

public record RecommendationResponse(
        UUID id,
        UUID trainerId,
        UUID userId,
        LocalDate date,
        String message
) {
}