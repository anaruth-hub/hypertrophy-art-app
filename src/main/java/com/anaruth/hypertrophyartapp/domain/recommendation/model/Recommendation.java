package com.anaruth.hypertrophyartapp.domain.recommendation.model;

import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;

import java.time.LocalDate;

public record Recommendation(
        RecommendationId id,
        TrainerId trainerId,
        UserId userId,
        LocalDate date,
        String message
) {

    public static Recommendation create(
            TrainerId trainerId,
            UserId userId,
            LocalDate date,
            String message
    ) {
        if (trainerId == null) {
            throw new IllegalArgumentException("Trainer id is required");
        }

        if (userId == null) {
            throw new IllegalArgumentException("User id is required");
        }

        if (date == null) {
            throw new IllegalArgumentException("Recommendation date is required");
        }

        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Recommendation message is required");
        }

        return new Recommendation(
                RecommendationId.generate(),
                trainerId,
                userId,
                date,
                message.trim()
        );
    }

    public static Recommendation restore(
            RecommendationId id,
            TrainerId trainerId,
            UserId userId,
            LocalDate date,
            String message
    ) {
        return new Recommendation(
                id,
                trainerId,
                userId,
                date,
                message
        );
    }
}