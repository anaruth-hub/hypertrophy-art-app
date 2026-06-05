package com.anaruth.hypertrophyartapp.infrastructure.controller.recovery;

import com.anaruth.hypertrophyartapp.domain.recovery.model.EnergyLevel;
import com.anaruth.hypertrophyartapp.domain.recovery.model.FatigueLevel;
import com.anaruth.hypertrophyartapp.domain.recovery.model.MuscleSorenessLevel;

import java.time.LocalDate;
import java.util.UUID;

public record RecoveryCheckInResponse(
        UUID id,
        UUID userId,
        LocalDate date,
        FatigueLevel fatigueLevel,
        MuscleSorenessLevel sorenessLevel,
        EnergyLevel energyLevel,
        double sleepHours,
        String notes
) {
}