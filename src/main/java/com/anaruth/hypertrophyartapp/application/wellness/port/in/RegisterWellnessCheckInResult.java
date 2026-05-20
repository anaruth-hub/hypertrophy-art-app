package com.anaruth.hypertrophyartapp.application.wellness.port.in;

import com.anaruth.hypertrophyartapp.domain.wellness.model.WellnessLevel;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterWellnessCheckInResult(
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