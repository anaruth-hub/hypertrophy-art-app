package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.recommendation;

import com.anaruth.hypertrophyartapp.application.recommendation.port.out.RecommendationRepository;
import com.anaruth.hypertrophyartapp.domain.recommendation.model.Recommendation;
import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MongoRecommendationPersistenceAdapter implements RecommendationRepository {

    private final MongoRecommendationRepository mongoRecommendationRepository;
    private final RecommendationMongoMapper recommendationMongoMapper =
            new RecommendationMongoMapper();

    public MongoRecommendationPersistenceAdapter(
            MongoRecommendationRepository mongoRecommendationRepository
    ) {
        this.mongoRecommendationRepository = mongoRecommendationRepository;
    }

    @Override
    public Recommendation save(Recommendation recommendation) {
        RecommendationDocument saved =
                mongoRecommendationRepository.save(
                        recommendationMongoMapper.toDocument(recommendation)
                );

        return recommendationMongoMapper.toDomain(saved);
    }

    @Override
    public List<Recommendation> findByUserId(UserId userId) {
        return mongoRecommendationRepository.findByUserId(userId.value().toString())
                .stream()
                .map(recommendationMongoMapper::toDomain)
                .toList();
    }

    @Override
    public List<Recommendation> findByTrainerIdAndUserId(
            TrainerId trainerId,
            UserId userId
    ) {
        return mongoRecommendationRepository
                .findByTrainerIdAndUserId(
                        trainerId.value().toString(),
                        userId.value().toString()
                )
                .stream()
                .map(recommendationMongoMapper::toDomain)
                .toList();
    }
}