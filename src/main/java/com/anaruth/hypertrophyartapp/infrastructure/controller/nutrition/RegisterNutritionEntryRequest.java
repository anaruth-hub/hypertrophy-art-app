package com.anaruth.hypertrophyartapp.infrastructure.controller.nutrition;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterNutritionEntryRequest(
        @NotNull UUID userId,
        @NotNull LocalDate date,
        @Min(0) int calories,
        @Min(0) double proteinGrams,
        @Min(0) double carbsGrams,
        @Min(0) double fatGrams,
        @Min(0) double hydrationLiters,
        String notes
) {
}