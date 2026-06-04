package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.recommendation;

import com.anaruth.hypertrophyartapp.domain.recommendation.model.Recommendation;
import com.anaruth.hypertrophyartapp.domain.recommendation.model.RecommendationId;
import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;

import java.util.UUID;

public class RecommendationMongoMapper {

    public RecommendationDocument toDocument(Recommendation recommendation) {
        return new RecommendationDocument(
                recommendation.id().value().toString(),
                recommendation.trainerId().value().toString(),
                recommendation.userId().value().toString(),
                recommendation.date(),
                recommendation.message()
        );
    }

    public Recommendation toDomain(RecommendationDocument document) {
        return Recommendation.restore(
                RecommendationId.from(UUID.fromString(document.getId())),
                TrainerId.from(UUID.fromString(document.getTrainerId())),
                UserId.from(UUID.fromString(document.getUserId())),
                document.getDate(),
                document.getMessage()
        );
    }
}