package com.anaruth.hypertrophyartapp.application.recommendation.port.in;

import java.util.List;
import java.util.UUID;

public interface ViewMyRecommendationsUseCase {

    List<RecommendationResult> viewMyRecommendations(UUID authenticatedUserId);
}