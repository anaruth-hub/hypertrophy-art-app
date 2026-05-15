package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.training;

import com.anaruth.hypertrophyartapp.domain.training.model.Training;
import com.anaruth.hypertrophyartapp.domain.training.model.TrainingId;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;

import java.util.UUID;

public class TrainingMongoMapper {

    public TrainingDocument toDocument(Training training) {
        return new TrainingDocument(
                training.id().value().toString(),
                training.userId().value().toString(),
                training.date(),
                training.muscleGroup(),
                training.exercises(),
                training.intensity(),
                training.durationMinutes()
        );
    }

    public Training toDomain(TrainingDocument document) {
        return Training.restore(
                TrainingId.from(UUID.fromString(document.getId())),
                UserId.from(UUID.fromString(document.getUserId())),
                document.getDate(),
                document.getMuscleGroup(),
                document.getExercises(),
                document.getIntensity(),
                document.getDurationMinutes()
        );
    }
}