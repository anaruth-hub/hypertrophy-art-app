package com.anaruth.hypertrophyartapp.infrastructure.controller.progress;

import com.anaruth.hypertrophyartapp.application.progress.port.in.ProgressSummaryResult;
import com.anaruth.hypertrophyartapp.application.progress.port.in.ViewAssignedUserProgressUseCase;
import com.anaruth.hypertrophyartapp.application.progress.port.in.ViewProgressSummaryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import com.anaruth.hypertrophyartapp.application.progress.port.in.ViewMyProgressSummaryUseCase;
import com.anaruth.hypertrophyartapp.infrastructure.security.AuthenticatedAccount;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.UUID;

@RestController
@RequestMapping("/api/progress-summary")
@Tag(name = "Progress", description = "User progress summary")
public class ProgressSummaryController {

    private final ViewProgressSummaryUseCase viewProgressSummaryUseCase;
    private final ViewAssignedUserProgressUseCase viewAssignedUserProgressUseCase;
    private final ViewMyProgressSummaryUseCase viewMyProgressSummaryUseCase;

    public ProgressSummaryController(
            ViewProgressSummaryUseCase viewProgressSummaryUseCase,
            ViewAssignedUserProgressUseCase viewAssignedUserProgressUseCase,
            ViewMyProgressSummaryUseCase viewMyProgressSummaryUseCase
    ) {
        this.viewProgressSummaryUseCase = viewProgressSummaryUseCase;
        this.viewAssignedUserProgressUseCase = viewAssignedUserProgressUseCase;
        this.viewMyProgressSummaryUseCase = viewMyProgressSummaryUseCase;
    }

    @GetMapping("/{userId}")
    @Operation(summary = "View progress summary by user id")
    public ProgressSummaryResponse viewByUserId(@PathVariable UUID userId) {
        ProgressSummaryResult result = viewProgressSummaryUseCase.viewByUserId(userId);

        return new ProgressSummaryResponse(
                result.userId(),
                result.totalTrainings(),
                result.latestTrainingDate(),
                result.latestTrainingMuscleGroup(),
                result.latestRecoveryFatigue(),
                result.latestRecoverySleepHours(),
                result.latestNutritionCalories(),
                result.latestNutritionProteinGrams(),
                result.latestWellnessStress(),
                result.latestWellnessMotivation()
        );
    }

    @GetMapping("/trainers/{trainerId}/users/{userId}")
    @Operation(summary = "Trainer views assigned user progress summary")
    public ProgressSummaryResponse viewAssignedUserProgress(
            @PathVariable UUID trainerId,
            @PathVariable UUID userId
    ) {
        ProgressSummaryResult result = viewAssignedUserProgressUseCase
                .viewAssignedUserProgress(trainerId, userId);

        return new ProgressSummaryResponse(
                result.userId(),
                result.totalTrainings(),
                result.latestTrainingDate(),
                result.latestTrainingMuscleGroup(),
                result.latestRecoveryFatigue(),
                result.latestRecoverySleepHours(),
                result.latestNutritionCalories(),
                result.latestNutritionProteinGrams(),
                result.latestWellnessStress(),
                result.latestWellnessMotivation()
        );
    }
    @GetMapping("/me")
    @Operation(summary = "View current authenticated user progress summary")
    public ProgressSummaryResponse viewMyProgressSummary(
            @AuthenticationPrincipal AuthenticatedAccount account
    ) {
        ProgressSummaryResult result =
                viewMyProgressSummaryUseCase.viewMyProgressSummary(account.id());

        return new ProgressSummaryResponse(
                result.userId(),
                result.totalTrainings(),
                result.latestTrainingDate(),
                result.latestTrainingMuscleGroup(),
                result.latestRecoveryFatigue(),
                result.latestRecoverySleepHours(),
                result.latestNutritionCalories(),
                result.latestNutritionProteinGrams(),
                result.latestWellnessStress(),
                result.latestWellnessMotivation()
        );
    }

}