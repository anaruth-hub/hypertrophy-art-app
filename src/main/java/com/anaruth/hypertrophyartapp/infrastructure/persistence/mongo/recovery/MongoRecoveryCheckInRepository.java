package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.recovery;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoRecoveryCheckInRepository
        extends MongoRepository<RecoveryCheckInDocument, String> {

    List<RecoveryCheckInDocument> findByUserId(String userId);
}