package com.anaruth.hypertrophyartapp.infrastructure.controller.recovery;

import com.anaruth.hypertrophyartapp.domain.recovery.model.EnergyLevel;
import com.anaruth.hypertrophyartapp.domain.recovery.model.FatigueLevel;
import com.anaruth.hypertrophyartapp.domain.recovery.model.MuscleSorenessLevel;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterRecoveryCheckInRequest(
        @NotNull UUID userId,
        @NotNull LocalDate date,
        @NotNull FatigueLevel fatigueLevel,
        @NotNull MuscleSorenessLevel sorenessLevel,
        @NotNull EnergyLevel energyLevel,
        @Min(0) @Max(24) double sleepHours,
        String notes
) {
}