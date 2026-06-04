package com.anaruth.hypertrophyartapp.application.recommendation.port.in;

import java.time.LocalDate;
import java.util.UUID;

public record RecommendationResult(
        UUID id,
        UUID trainerId,
        UUID userId,
        LocalDate date,
        String message
) {
}