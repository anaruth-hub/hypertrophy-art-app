package com.anaruth.hypertrophyartapp.infrastructure.controller.recommendation;

import java.time.LocalDate;

public record CreateRecommendationRequest(
        LocalDate date,
        String message
) {
}