package com.anaruth.hypertrophyartapp.infrastructure.controller.recommendation;

import java.util.UUID;

public record DeloadRecommendationResponse(
        UUID userId,
        String recommendation,
        String reason
) {
}