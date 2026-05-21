package com.anaruth.hypertrophyartapp.infrastructure.controller.wellness;

import com.anaruth.hypertrophyartapp.domain.wellness.model.WellnessLevel;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterWellnessCheckInRequest(
        @NotNull UUID userId,
        @NotNull LocalDate date,
        @NotNull WellnessLevel physicalState,
        @NotNull WellnessLevel mentalState,
        @NotNull WellnessLevel emotionalState,
        @NotNull WellnessLevel stressLevel,
        @NotNull WellnessLevel motivationLevel,
        String notes
) {
}