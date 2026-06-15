package com.anaruth.hypertrophyartapp.infrastructure.controller.recovery;

import com.anaruth.hypertrophyartapp.application.recovery.port.in.RegisterRecoveryCheckInCommand;
import com.anaruth.hypertrophyartapp.application.recovery.port.in.RegisterRecoveryCheckInResult;
import com.anaruth.hypertrophyartapp.application.recovery.port.in.RegisterRecoveryCheckInUseCase;
import com.anaruth.hypertrophyartapp.domain.auth.model.Role;
import com.anaruth.hypertrophyartapp.infrastructure.security.AuthenticatedAccount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public RecoveryCheckInResponse register(
            @AuthenticationPrincipal AuthenticatedAccount account,
            @Valid @RequestBody RegisterRecoveryCheckInRequest request
    ) {
        requireRole(account, Role.USER);

        RegisterRecoveryCheckInResult result = registerRecoveryCheckInUseCase.register(
                new RegisterRecoveryCheckInCommand(
                        account.id(),
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

    private void requireRole(AuthenticatedAccount account, Role role) {
        if (account == null || account.role() != role) {
            throw new AccessDeniedException("Required role: " + role);
        }
    }
}
