package com.anaruth.hypertrophyartapp.infrastructure.controller.nutrition;

import java.time.LocalDate;
import java.util.UUID;

public record NutritionEntryResponse(
        UUID id,
        UUID userId,
        LocalDate date,
        int calories,
        double proteinGrams,
        double carbsGrams,
        double fatGrams,
        double hydrationLiters,
        String notes
) {
}