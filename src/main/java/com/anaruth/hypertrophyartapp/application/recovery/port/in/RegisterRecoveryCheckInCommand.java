package com.anaruth.hypertrophyartapp.application.recovery.port.in;

import com.anaruth.hypertrophyartapp.domain.recovery.model.EnergyLevel;
import com.anaruth.hypertrophyartapp.domain.recovery.model.FatigueLevel;
import com.anaruth.hypertrophyartapp.domain.recovery.model.MuscleSorenessLevel;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterRecoveryCheckInCommand(
        UUID userId,
        LocalDate date,
        FatigueLevel fatigueLevel,
        MuscleSorenessLevel sorenessLevel,
        EnergyLevel energyLevel,
        double sleepHours,
        String notes
) {
}