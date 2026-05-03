package com.anaruth.hypertrophyartapp.application.training.port.in;

import com.anaruth.hypertrophyartapp.domain.training.model.TrainingIntensity;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterTrainingResult(
        UUID id,
        UUID userId,
        LocalDate date,
        String muscleGroup,
        String exercises,
        TrainingIntensity intensity,
        int durationMinutes
) {
}