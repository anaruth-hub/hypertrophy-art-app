package com.anaruth.hypertrophyartapp.infrastructure.controller.recommendation;

import com.anaruth.hypertrophyartapp.application.recommendation.port.in.DeloadRecommendationResult;
import com.anaruth.hypertrophyartapp.application.recommendation.port.in.ViewDeloadRecommendationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/recommendations")
@Tag(name = "Recommendations", description = "Training load and deload recommendations")
public class DeloadRecommendationController {

    private final ViewDeloadRecommendationUseCase viewDeloadRecommendationUseCase;

    public DeloadRecommendationController(
            ViewDeloadRecommendationUseCase viewDeloadRecommendationUseCase
    ) {
        this.viewDeloadRecommendationUseCase = viewDeloadRecommendationUseCase;
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
}