package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.nutrition;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MongoNutritionEntryRepository
        extends MongoRepository<NutritionEntryDocument, String> {

    List<NutritionEntryDocument> findByUserId(String userId);
}