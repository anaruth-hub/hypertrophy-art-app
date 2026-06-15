package com.anaruth.hypertrophyartapp.infrastructure.controller.wellness;

import com.anaruth.hypertrophyartapp.application.wellness.port.in.RegisterWellnessCheckInCommand;
import com.anaruth.hypertrophyartapp.application.wellness.port.in.RegisterWellnessCheckInResult;
import com.anaruth.hypertrophyartapp.application.wellness.port.in.RegisterWellnessCheckInUseCase;
import com.anaruth.hypertrophyartapp.domain.auth.model.Role;
import com.anaruth.hypertrophyartapp.infrastructure.security.AuthenticatedAccount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public WellnessCheckInResponse register(
            @AuthenticationPrincipal AuthenticatedAccount account,
            @Valid @RequestBody RegisterWellnessCheckInRequest request
    ) {
        requireRole(account, Role.USER);

        RegisterWellnessCheckInResult result = registerWellnessCheckInUseCase.register(
                new RegisterWellnessCheckInCommand(
                        account.id(),
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

    private void requireRole(AuthenticatedAccount account, Role role) {
        if (account == null || account.role() != role) {
            throw new AccessDeniedException("Required role: " + role);
        }
    }
}
