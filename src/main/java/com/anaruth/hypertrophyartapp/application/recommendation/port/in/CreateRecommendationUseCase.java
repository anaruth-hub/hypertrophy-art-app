package com.anaruth.hypertrophyartapp.application.recommendation.port.in;

public interface CreateRecommendationUseCase {

    RecommendationResult createRecommendation(CreateRecommendationCommand command);
}