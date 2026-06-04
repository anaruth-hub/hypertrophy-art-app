package com.anaruth.hypertrophyartapp.infrastructure.controller.recommendation;

import com.anaruth.hypertrophyartapp.application.recommendation.port.in.DeloadRecommendationResult;
import com.anaruth.hypertrophyartapp.application.recommendation.port.in.ViewDeloadRecommendationUseCase;
import com.anaruth.hypertrophyartapp.application.recommendation.port.in.CreateRecommendationCommand;
import com.anaruth.hypertrophyartapp.application.recommendation.port.in.CreateRecommendationUseCase;
import com.anaruth.hypertrophyartapp.application.recommendation.port.in.RecommendationResult;
import com.anaruth.hypertrophyartapp.application.recommendation.port.in.ViewMyRecommendationsUseCase;
import com.anaruth.hypertrophyartapp.infrastructure.security.AuthenticatedAccount;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/recommendations")
@Tag(name = "Recommendations", description = "Training load and deload recommendations")
public class DeloadRecommendationController {

    private final ViewDeloadRecommendationUseCase viewDeloadRecommendationUseCase;
    private final CreateRecommendationUseCase createRecommendationUseCase;
    private final ViewMyRecommendationsUseCase viewMyRecommendationsUseCase;

    public DeloadRecommendationController(
            ViewDeloadRecommendationUseCase viewDeloadRecommendationUseCase,
            CreateRecommendationUseCase createRecommendationUseCase,
            ViewMyRecommendationsUseCase viewMyRecommendationsUseCase
    ) {
        this.viewDeloadRecommendationUseCase = viewDeloadRecommendationUseCase;
        this.createRecommendationUseCase = createRecommendationUseCase;
        this.viewMyRecommendationsUseCase = viewMyRecommendationsUseCase;
    }

    @GetMapping("/deload/{userId}")
    @Operation(summary = "View deload recommendation by user id")
    public DeloadRecommendationResponse viewByUserId(@PathVariable UUID userId) {
        DeloadRecommendationResult result =
                viewDeloadRecommendationUseCase.viewByUserId(userId);

        return new DeloadRecommendationResponse(
                result.userId(),
                result.recommendation(),
                result.reason()
        );
    }

    @PostMapping("/trainers/me/users/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Trainer creates recommendation for assigned user")
    public RecommendationResponse createRecommendation(
            @AuthenticationPrincipal AuthenticatedAccount account,
            @PathVariable UUID userId,
            @RequestBody CreateRecommendationRequest request
    ) {
        RecommendationResult result =
                createRecommendationUseCase.createRecommendation(
                        new CreateRecommendationCommand(
                                account.id(),
                                userId,
                                request.date(),
                                request.message()
                        )
                );

        return toRecommendationResponse(result);
    }

    @GetMapping("/me")
    @Operation(summary = "Current user views received recommendations")
    public List<RecommendationResponse> viewMyRecommendations(
            @AuthenticationPrincipal AuthenticatedAccount account
    ) {
        return viewMyRecommendationsUseCase.viewMyRecommendations(account.id())
                .stream()
                .map(this::toRecommendationResponse)
                .toList();
    }

    private RecommendationResponse toRecommendationResponse(RecommendationResult result) {
        return new RecommendationResponse(
                result.id(),
                result.trainerId(),
                result.userId(),
                result.date(),
                result.message()
        );
    }
}