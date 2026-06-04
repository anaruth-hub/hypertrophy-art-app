package com.anaruth.hypertrophyartapp.application.recommendation.port.out;

import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import com.anaruth.hypertrophyartapp.domain.recommendation.model.Recommendation;

import java.util.List;

public interface RecommendationRepository {

    Recommendation save(Recommendation recommendation);

    List<Recommendation> findByUserId(UserId userId);

    List<Recommendation> findByTrainerIdAndUserId(
            TrainerId trainerId,
            UserId userId
    );
}