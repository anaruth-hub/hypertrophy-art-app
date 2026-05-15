package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.training;

import com.anaruth.hypertrophyartapp.application.training.port.out.TrainingRepository;
import com.anaruth.hypertrophyartapp.domain.training.model.Training;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class MongoTrainingPersistenceAdapter implements TrainingRepository {

    private final SpringDataTrainingMongoRepository repository;
    private final TrainingMongoMapper mapper = new TrainingMongoMapper();

    public MongoTrainingPersistenceAdapter(SpringDataTrainingMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Training save(Training training) {
        TrainingDocument document = mapper.toDocument(training);
        TrainingDocument saved = repository.save(document);
        return mapper.toDomain(saved);
    }
}