package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.training;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface SpringDataTrainingMongoRepository
        extends MongoRepository<TrainingDocument, String> {

    List<TrainingDocument> findByUserId(String userId);
}