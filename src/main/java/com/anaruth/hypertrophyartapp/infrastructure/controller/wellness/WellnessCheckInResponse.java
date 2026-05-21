package com.anaruth.hypertrophyartapp.infrastructure.controller.wellness;

import com.anaruth.hypertrophyartapp.domain.wellness.model.WellnessLevel;

import java.time.LocalDate;
import java.util.UUID;

public record WellnessCheckInResponse(
        UUID id,
        UUID userId,
        LocalDate date,
        WellnessLevel physicalState,
        WellnessLevel mentalState,
        WellnessLevel emotionalState,
        WellnessLevel stressLevel,
        WellnessLevel motivationLevel,
        String notes
) {
}