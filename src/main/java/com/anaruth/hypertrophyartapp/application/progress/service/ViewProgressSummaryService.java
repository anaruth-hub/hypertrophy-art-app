package com.anaruth.hypertrophyartapp.application.progress.service;

import com.anaruth.hypertrophyartapp.application.nutrition.port.out.NutritionEntryRepository;
import com.anaruth.hypertrophyartapp.application.progress.port.in.ProgressSummaryResult;
import com.anaruth.hypertrophyartapp.application.progress.port.in.ViewProgressSummaryUseCase;
import com.anaruth.hypertrophyartapp.application.recovery.port.out.RecoveryCheckInRepository;
import com.anaruth.hypertrophyartapp.application.training.port.out.TrainingRepository;
import com.anaruth.hypertrophyartapp.application.user.port.out.UserRepository;
import com.anaruth.hypertrophyartapp.application.wellness.port.out.WellnessCheckInRepository;
import com.anaruth.hypertrophyartapp.domain.nutrition.model.NutritionEntry;
import com.anaruth.hypertrophyartapp.domain.recovery.model.RecoveryCheckIn;
import com.anaruth.hypertrophyartapp.domain.training.model.Training;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import com.anaruth.hypertrophyartapp.domain.wellness.model.WellnessCheckIn;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class ViewProgressSummaryService implements ViewProgressSummaryUseCase {

    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;
    private final RecoveryCheckInRepository recoveryCheckInRepository;
    private final NutritionEntryRepository nutritionEntryRepository;
    private final WellnessCheckInRepository wellnessCheckInRepository;

    public ViewProgressSummaryService(
            UserRepository userRepository,
            TrainingRepository trainingRepository,
            RecoveryCheckInRepository recoveryCheckInRepository,
            NutritionEntryRepository nutritionEntryRepository,
            WellnessCheckInRepository wellnessCheckInRepository
    ) {
        this.userRepository = userRepository;
        this.trainingRepository = trainingRepository;
        this.recoveryCheckInRepository = recoveryCheckInRepository;
        this.nutritionEntryRepository = nutritionEntryRepository;
        this.wellnessCheckInRepository = wellnessCheckInRepository;
    }

    @Override
    public ProgressSummaryResult viewByUserId(UUID userUuid) {
        UserId userId = UserId.from(userUuid);

        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Training> trainings = trainingRepository.findByUserId(userId);
        List<RecoveryCheckIn> recoveries = recoveryCheckInRepository.findByUserId(userId);
        List<NutritionEntry> nutritionEntries = nutritionEntryRepository.findByUserId(userId);
        List<WellnessCheckIn> wellnessCheckIns = wellnessCheckInRepository.findByUserId(userId);

        Training latestTraining = trainings.stream()
                .max(Comparator.comparing(Training::date))
                .orElse(null);

        RecoveryCheckIn latestRecovery = recoveries.stream()
                .max(Comparator.comparing(RecoveryCheckIn::date))
                .orElse(null);

        NutritionEntry latestNutrition = nutritionEntries.stream()
                .max(Comparator.comparing(NutritionEntry::date))
                .orElse(null);

        WellnessCheckIn latestWellness = wellnessCheckIns.stream()
                .max(Comparator.comparing(WellnessCheckIn::date))
                .orElse(null);

        return new ProgressSummaryResult(
                userUuid,
                trainings.size(),
                latestTraining == null ? null : latestTraining.date(),
                latestTraining == null ? null : latestTraining.muscleGroup(),
                latestRecovery == null ? null : latestRecovery.fatigueLevel(),
                latestRecovery == null ? 0 : latestRecovery.sleepHours(),
                latestNutrition == null ? null : latestNutrition.calories(),
                latestNutrition == null ? null : latestNutrition.proteinGrams(),
                latestWellness == null ? null : latestWellness.stressLevel(),
                latestWellness == null ? null : latestWellness.motivationLevel()
        );
    }
}