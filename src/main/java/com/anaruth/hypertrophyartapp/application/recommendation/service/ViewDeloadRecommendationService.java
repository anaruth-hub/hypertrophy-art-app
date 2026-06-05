package com.anaruth.hypertrophyartapp.application.recommendation.service;

import com.anaruth.hypertrophyartapp.application.recommendation.port.in.DeloadRecommendationResult;
import com.anaruth.hypertrophyartapp.application.recommendation.port.in.ViewDeloadRecommendationUseCase;
import com.anaruth.hypertrophyartapp.application.recovery.port.out.RecoveryCheckInRepository;
import com.anaruth.hypertrophyartapp.application.training.port.out.TrainingRepository;
import com.anaruth.hypertrophyartapp.application.user.port.out.UserRepository;
import com.anaruth.hypertrophyartapp.application.wellness.port.out.WellnessCheckInRepository;
import com.anaruth.hypertrophyartapp.domain.recovery.model.FatigueLevel;
import com.anaruth.hypertrophyartapp.domain.recovery.model.RecoveryCheckIn;
import com.anaruth.hypertrophyartapp.domain.training.model.Training;
import com.anaruth.hypertrophyartapp.domain.training.model.TrainingIntensity;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import com.anaruth.hypertrophyartapp.domain.wellness.model.WellnessCheckIn;
import com.anaruth.hypertrophyartapp.domain.wellness.model.WellnessLevel;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class ViewDeloadRecommendationService implements ViewDeloadRecommendationUseCase {

    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;
    private final RecoveryCheckInRepository recoveryCheckInRepository;
    private final WellnessCheckInRepository wellnessCheckInRepository;

    public ViewDeloadRecommendationService(
            UserRepository userRepository,
            TrainingRepository trainingRepository,
            RecoveryCheckInRepository recoveryCheckInRepository,
            WellnessCheckInRepository wellnessCheckInRepository
    ) {
        this.userRepository = userRepository;
        this.trainingRepository = trainingRepository;
        this.recoveryCheckInRepository = recoveryCheckInRepository;
        this.wellnessCheckInRepository = wellnessCheckInRepository;
    }

    @Override
    public DeloadRecommendationResult viewByUserId(UUID userUuid) {
        UserId userId = UserId.from(userUuid);

        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Training> trainings = trainingRepository.findByUserId(userId);
        List<RecoveryCheckIn> recoveries = recoveryCheckInRepository.findByUserId(userId);
        List<WellnessCheckIn> wellnessCheckIns = wellnessCheckInRepository.findByUserId(userId);

        Training latestTraining = trainings.stream()
                .max(Comparator.comparing(Training::date))
                .orElse(null);

        RecoveryCheckIn latestRecovery = recoveries.stream()
                .max(Comparator.comparing(RecoveryCheckIn::date))
                .orElse(null);

        WellnessCheckIn latestWellness = wellnessCheckIns.stream()
                .max(Comparator.comparing(WellnessCheckIn::date))
                .orElse(null);

        boolean highTrainingIntensity =
                latestTraining != null && latestTraining.intensity() == TrainingIntensity.HIGH;

        boolean highFatigue =
                latestRecovery != null && latestRecovery.fatigueLevel() == FatigueLevel.HIGH;

        boolean lowSleep =
                latestRecovery != null && latestRecovery.sleepHours() < 6;

        boolean highStress =
                latestWellness != null && latestWellness.stressLevel() == WellnessLevel.HIGH;

        boolean lowMotivation =
                latestWellness != null && latestWellness.motivationLevel() == WellnessLevel.LOW;

        if (highFatigue && lowSleep) {
            return new DeloadRecommendationResult(
                    userUuid,
                    "Deload recommended",
                    "High fatigue and low sleep detected"
            );
        }

        if (highStress && lowMotivation) {
            return new DeloadRecommendationResult(
                    userUuid,
                    "Deload recommended",
                    "High stress and low motivation detected"
            );
        }

        if (highTrainingIntensity && (highFatigue || highStress)) {
            return new DeloadRecommendationResult(
                    userUuid,
                    "Reduce training load",
                    "High training intensity combined with fatigue or stress detected"
            );
        }

        return new DeloadRecommendationResult(
                userUuid,
                "Recovery status is stable",
                "No critical fatigue indicators detected"
        );
    }
}