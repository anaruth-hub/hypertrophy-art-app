package com.anaruth.hypertrophyartapp.application.recommendation.port.in;

import java.util.UUID;

public record DeloadRecommendationResult(
        UUID userId,
        String recommendation,
        String reason
) {
}