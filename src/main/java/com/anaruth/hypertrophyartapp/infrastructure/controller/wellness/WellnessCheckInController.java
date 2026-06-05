package com.anaruth.hypertrophyartapp.infrastructure.controller.wellness;

import com.anaruth.hypertrophyartapp.application.wellness.port.in.RegisterWellnessCheckInCommand;
import com.anaruth.hypertrophyartapp.application.wellness.port.in.RegisterWellnessCheckInResult;
import com.anaruth.hypertrophyartapp.application.wellness.port.in.RegisterWellnessCheckInUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wellness-checkins")
@Tag(name = "Wellness", description = "Physical, mental and emotional wellbeing tracking")
public class WellnessCheckInController {

    private final RegisterWellnessCheckInUseCase registerWellnessCheckInUseCase;

    public WellnessCheckInController(RegisterWellnessCheckInUseCase registerWellnessCheckInUseCase) {
        this.registerWellnessCheckInUseCase = registerWellnessCheckInUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register wellness check-in")
    public WellnessCheckInResponse register(@Valid @RequestBody RegisterWellnessCheckInRequest request) {
        RegisterWellnessCheckInResult result = registerWellnessCheckInUseCase.register(
                new RegisterWellnessCheckInCommand(
                        request.userId(),
                        request.date(),
                        request.physicalState(),
                        request.mentalState(),
                        request.emotionalState(),
                        request.stressLevel(),
                        request.motivationLevel(),
                        request.notes()
                )
        );

        return new WellnessCheckInResponse(
                result.id(),
                result.userId(),
                result.date(),
                result.physicalState(),
                result.mentalState(),
                result.emotionalState(),
                result.stressLevel(),
                result.motivationLevel(),
                result.notes()
        );
    }
}