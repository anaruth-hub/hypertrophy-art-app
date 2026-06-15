package com.anaruth.hypertrophyartapp.infrastructure.controller.training;

import com.anaruth.hypertrophyartapp.application.training.port.in.RegisterTrainingCommand;
import com.anaruth.hypertrophyartapp.application.training.port.in.RegisterTrainingResult;
import com.anaruth.hypertrophyartapp.application.training.port.in.RegisterTrainingUseCase;
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
@RequestMapping("/api/trainings")
@Tag(name = "Trainings", description = "Training session tracking")
public class TrainingController {

    private final RegisterTrainingUseCase registerTrainingUseCase;

    public TrainingController(RegisterTrainingUseCase registerTrainingUseCase) {
        this.registerTrainingUseCase = registerTrainingUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register training session")
    public TrainingResponse registerTraining(
            @AuthenticationPrincipal AuthenticatedAccount account,
            @Valid @RequestBody RegisterTrainingRequest request
    ) {
        requireRole(account, Role.USER);

        RegisterTrainingResult result = registerTrainingUseCase.registerTraining(
                new RegisterTrainingCommand(
                        account.id(),
                        request.date(),
                        request.muscleGroup(),
                        request.exercises(),
                        request.intensity(),
                        request.durationMinutes()
                )
        );

        return new TrainingResponse(
                result.id(),
                result.userId(),
                result.date(),
                result.muscleGroup(),
                result.exercises(),
                result.intensity(),
                result.durationMinutes()
        );
    }

    private void requireRole(AuthenticatedAccount account, Role role) {
        if (account == null || account.role() != role) {
            throw new AccessDeniedException("Required role: " + role);
        }
    }
}
