package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.wellness;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MongoWellnessCheckInRepository
        extends MongoRepository<WellnessCheckInDocument, String> {

    List<WellnessCheckInDocument> findByUserId(String userId);
}