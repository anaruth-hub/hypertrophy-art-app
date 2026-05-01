package com.anaruth.hypertrophyartapp.infrastructure.persistence.memory;

import com.anaruth.hypertrophyartapp.application.trainer.port.out.TrainerRepository;
import com.anaruth.hypertrophyartapp.domain.trainer.model.Trainer;
import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryTrainerRepository implements TrainerRepository {

    private final Map<UUID, Trainer> trainers = new ConcurrentHashMap<>();

    @Override
    public Trainer save(Trainer trainer) {
        trainers.put(trainer.id().value(), trainer);
        return trainer;
    }

    @Override
    public Optional<Trainer> findById(TrainerId trainerId) {
        return Optional.ofNullable(trainers.get(trainerId.value()));
    }
}