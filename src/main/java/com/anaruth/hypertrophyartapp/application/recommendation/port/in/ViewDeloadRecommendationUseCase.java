package com.anaruth.hypertrophyartapp.application.recommendation.port.in;

import java.util.UUID;

public interface ViewDeloadRecommendationUseCase {

    DeloadRecommendationResult viewByUserId(UUID userId);
}