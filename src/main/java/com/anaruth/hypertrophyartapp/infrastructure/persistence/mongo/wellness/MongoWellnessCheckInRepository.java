package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.wellness;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoWellnessCheckInRepository
        extends MongoRepository<WellnessCheckInDocument, String> {
}