package com.anaruth.hypertrophyartapp.infrastructure.persistence.memory;

import com.anaruth.hypertrophyartapp.application.training.port.out.TrainingRepository;
import com.anaruth.hypertrophyartapp.domain.training.model.Training;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryTrainingRepository implements TrainingRepository {

    private final Map<UUID, Training> trainings = new ConcurrentHashMap<>();

    @Override
    public Training save(Training training) {
        trainings.put(training.id().value(), training);
        return training;
    }
}