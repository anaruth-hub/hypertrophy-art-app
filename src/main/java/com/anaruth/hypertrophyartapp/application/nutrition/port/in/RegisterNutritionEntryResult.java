package com.anaruth.hypertrophyartapp.application.nutrition.port.in;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterNutritionEntryResult(
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