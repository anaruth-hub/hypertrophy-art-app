package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.recovery;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoRecoveryCheckInRepository
        extends MongoRepository<RecoveryCheckInDocument, String> {
}