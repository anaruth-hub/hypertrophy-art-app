package com.anaruth.hypertrophyartapp.infrastructure.persistence.postgres.trainer;

import com.anaruth.hypertrophyartapp.domain.trainer.model.Trainer;
import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;

import java.util.UUID;

public class TrainerJpaMapper {

    public TrainerEntity toEntity(Trainer trainer) {
        return new TrainerEntity(
                trainer.id().value().toString(),
                trainer.name(),
                trainer.email()
        );
    }

    public Trainer toDomain(TrainerEntity entity) {
        return Trainer.restore(
                TrainerId.from(UUID.fromString(entity.getId())),
                entity.getName(),
                entity.getEmail()
        );
    }
}