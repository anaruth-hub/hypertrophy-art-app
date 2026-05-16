package com.anaruth.hypertrophyartapp.infrastructure.controller.recovery;

import com.anaruth.hypertrophyartapp.application.recovery.port.in.RegisterRecoveryCheckInCommand;
import com.anaruth.hypertrophyartapp.application.recovery.port.in.RegisterRecoveryCheckInResult;
import com.anaruth.hypertrophyartapp.application.recovery.port.in.RegisterRecoveryCheckInUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recovery-checkins")
@Tag(name = "Recovery", description = "Recovery and fatigue tracking")
public class RecoveryCheckInController {

    private final RegisterRecoveryCheckInUseCase registerRecoveryCheckInUseCase;

    public RecoveryCheckInController(RegisterRecoveryCheckInUseCase registerRecoveryCheckInUseCase) {
        this.registerRecoveryCheckInUseCase = registerRecoveryCheckInUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register recovery and fatigue check-in")
    public RecoveryCheckInResponse register(@Valid @RequestBody RegisterRecoveryCheckInRequest request) {
        RegisterRecoveryCheckInResult result = registerRecoveryCheckInUseCase.register(
                new RegisterRecoveryCheckInCommand(
                        request.userId(),
                        request.date(),
                        request.fatigueLevel(),
                        request.sorenessLevel(),
                        request.energyLevel(),
                        request.sleepHours(),
                        request.notes()
                )
        );

        return new RecoveryCheckInResponse(
                result.id(),
                result.userId(),
                result.date(),
                result.fatigueLevel(),
                result.sorenessLevel(),
                result.energyLevel(),
                result.sleepHours(),
                result.notes()
        );
    }
}