package com.anaruth.hypertrophyartapp.infrastructure.persistence.postgres.trainer;

import com.anaruth.hypertrophyartapp.application.trainer.port.out.TrainerRepository;
import com.anaruth.hypertrophyartapp.domain.trainer.model.Trainer;
import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public class PostgresTrainerPersistenceAdapter implements TrainerRepository {

    private final SpringDataTrainerJpaRepository repository;
    private final TrainerJpaMapper mapper = new TrainerJpaMapper();

    public PostgresTrainerPersistenceAdapter(SpringDataTrainerJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Trainer save(Trainer trainer) {
        TrainerEntity entity = mapper.toEntity(trainer);
        TrainerEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Trainer> findById(TrainerId id) {
        return repository.findById(id.value().toString())
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Trainer> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(mapper::toDomain);
    }
}