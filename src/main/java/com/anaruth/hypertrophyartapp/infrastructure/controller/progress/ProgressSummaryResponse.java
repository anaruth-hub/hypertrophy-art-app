package com.anaruth.hypertrophyartapp.infrastructure.controller.progress;

import com.anaruth.hypertrophyartapp.domain.recovery.model.FatigueLevel;
import com.anaruth.hypertrophyartapp.domain.wellness.model.WellnessLevel;

import java.time.LocalDate;
import java.util.UUID;

public record ProgressSummaryResponse(
        UUID userId,
        int totalTrainings,
        LocalDate latestTrainingDate,
        String latestTrainingMuscleGroup,
        FatigueLevel latestRecoveryFatigue,
        double latestRecoverySleepHours,
        Integer latestNutritionCalories,
        Double latestNutritionProteinGrams,
        WellnessLevel latestWellnessStress,
        WellnessLevel latestWellnessMotivation
) {
}