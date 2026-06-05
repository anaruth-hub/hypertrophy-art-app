package com.anaruth.hypertrophyartapp.application.recommendation.service;

import com.anaruth.hypertrophyartapp.application.recommendation.port.in.CreateRecommendationCommand;
import com.anaruth.hypertrophyartapp.application.recommendation.port.in.CreateRecommendationUseCase;
import com.anaruth.hypertrophyartapp.application.recommendation.port.in.RecommendationResult;
import com.anaruth.hypertrophyartapp.application.recommendation.port.out.RecommendationRepository;
import com.anaruth.hypertrophyartapp.application.user.port.out.UserRepository;
import com.anaruth.hypertrophyartapp.domain.recommendation.model.Recommendation;
import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;
import com.anaruth.hypertrophyartapp.domain.user.model.User;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import org.springframework.stereotype.Service;

@Service
public class CreateRecommendationService implements CreateRecommendationUseCase {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;

    public CreateRecommendationService(
            RecommendationRepository recommendationRepository,
            UserRepository userRepository
    ) {
        this.recommendationRepository = recommendationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RecommendationResult createRecommendation(CreateRecommendationCommand command) {
        TrainerId trainerId = TrainerId.from(command.authenticatedTrainerId());
        UserId userId = UserId.from(command.userId());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.trainerId() == null || !user.trainerId().equals(trainerId)) {
            throw new IllegalArgumentException("User is not assigned to this trainer");
        }

        Recommendation recommendation = Recommendation.create(
                trainerId,
                userId,
                command.date(),
                command.message()
        );

        Recommendation savedRecommendation =
                recommendationRepository.save(recommendation);

        return toResult(savedRecommendation);
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