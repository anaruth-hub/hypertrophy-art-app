package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.nutrition;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoNutritionEntryRepository
        extends MongoRepository<NutritionEntryDocument, String> {
}