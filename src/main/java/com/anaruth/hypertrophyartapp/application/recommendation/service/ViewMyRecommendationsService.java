package com.anaruth.hypertrophyartapp.application.recommendation.service;

import com.anaruth.hypertrophyartapp.application.recommendation.port.in.RecommendationResult;
import com.anaruth.hypertrophyartapp.application.recommendation.port.in.ViewMyRecommendationsUseCase;
import com.anaruth.hypertrophyartapp.application.recommendation.port.out.RecommendationRepository;
import com.anaruth.hypertrophyartapp.domain.recommendation.model.Recommendation;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ViewMyRecommendationsService implements ViewMyRecommendationsUseCase {

    private final RecommendationRepository recommendationRepository;

    public ViewMyRecommendationsService(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public List<RecommendationResult> viewMyRecommendations(UUID authenticatedUserId) {
        return recommendationRepository.findByUserId(UserId.from(authenticatedUserId))
                .stream()
                .map(this::toResult)
                .toList();
    }

    private RecommendationResult toResult(Recommendation recommendation) {
        return new RecommendationResult(
                recommendation.id().value(),
                recommendation.trainerId().value(),
                recommendation.userId().value(),
                recommendation.date(),
                recommendation.message()
        );
    }
}