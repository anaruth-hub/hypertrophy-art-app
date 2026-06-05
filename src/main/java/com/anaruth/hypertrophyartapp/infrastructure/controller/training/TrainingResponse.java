package com.anaruth.hypertrophyartapp.infrastructure.controller.training;

import com.anaruth.hypertrophyartapp.domain.training.model.TrainingIntensity;

import java.time.LocalDate;
import java.util.UUID;

public record TrainingResponse(
        UUID id,
        UUID userId,
        LocalDate date,
        String muscleGroup,
        String exercises,
        TrainingIntensity intensity,
        int durationMinutes
) {
}