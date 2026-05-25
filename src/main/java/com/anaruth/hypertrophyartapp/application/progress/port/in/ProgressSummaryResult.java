package com.anaruth.hypertrophyartapp.application.progress.port.in;

import com.anaruth.hypertrophyartapp.domain.recovery.model.FatigueLevel;
import com.anaruth.hypertrophyartapp.domain.wellness.model.WellnessLevel;

import java.time.LocalDate;
import java.util.UUID;

public record ProgressSummaryResult(
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