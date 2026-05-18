package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.nutrition;

import com.anaruth.hypertrophyartapp.application.nutrition.port.out.NutritionEntryRepository;
import com.anaruth.hypertrophyartapp.domain.nutrition.model.NutritionEntry;
import org.springframework.stereotype.Component;

@Component
public class MongoNutritionEntryPersistenceAdapter implements NutritionEntryRepository {

    private final MongoNutritionEntryRepository repository;
    private final NutritionEntryMongoMapper mapper = new NutritionEntryMongoMapper();

    public MongoNutritionEntryPersistenceAdapter(MongoNutritionEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public NutritionEntry save(NutritionEntry nutritionEntry) {
        NutritionEntryDocument document = mapper.toDocument(nutritionEntry);
        NutritionEntryDocument saved = repository.save(document);
        return mapper.toDomain(saved);
    }
}