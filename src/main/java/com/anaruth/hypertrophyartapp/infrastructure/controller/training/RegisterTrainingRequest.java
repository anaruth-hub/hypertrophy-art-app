package com.anaruth.hypertrophyartapp.infrastructure.controller.training;

import com.anaruth.hypertrophyartapp.domain.training.model.TrainingIntensity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterTrainingRequest(
        @NotNull UUID userId,
        @NotNull LocalDate date,
        @NotBlank String muscleGroup,
        @NotBlank String exercises,
        @NotNull TrainingIntensity intensity,
        @Min(1) int durationMinutes
) {
}