package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.recommendation;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoRecommendationRepository
        extends MongoRepository<RecommendationDocument, String> {

    List<RecommendationDocument> findByUserId(String userId);

    List<RecommendationDocument> findByTrainerIdAndUserId(
            String trainerId,
            String userId
    );
}