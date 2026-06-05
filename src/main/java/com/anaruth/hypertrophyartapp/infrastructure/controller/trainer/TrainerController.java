package com.anaruth.hypertrophyartapp.infrastructure.controller.trainer;

import com.anaruth.hypertrophyartapp.application.trainer.port.in.CreateTrainerCommand;
import com.anaruth.hypertrophyartapp.application.trainer.port.in.CreateTrainerResult;
import com.anaruth.hypertrophyartapp.application.trainer.port.in.CreateTrainerUseCase;
import com.anaruth.hypertrophyartapp.application.trainer.port.in.SupervisedUserResult;
import com.anaruth.hypertrophyartapp.application.trainer.port.in.ViewMySupervisedUsersUseCase;
import com.anaruth.hypertrophyartapp.infrastructure.security.AuthenticatedAccount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
@Tag(name = "Trainers", description = "Trainer profile management")
public class TrainerController {

    private final CreateTrainerUseCase createTrainerUseCase;
    private final ViewMySupervisedUsersUseCase viewMySupervisedUsersUseCase;

    public TrainerController(
            CreateTrainerUseCase createTrainerUseCase,
            ViewMySupervisedUsersUseCase viewMySupervisedUsersUseCase
    ) {
        this.createTrainerUseCase = createTrainerUseCase;
        this.viewMySupervisedUsersUseCase = viewMySupervisedUsersUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create trainer profile")
    public TrainerResponse createTrainer(@Valid @RequestBody CreateTrainerRequest request) {
        CreateTrainerResult result = createTrainerUseCase.createTrainer(
                new CreateTrainerCommand(request.name(), request.email())
        );

        return new TrainerResponse(
                result.id(),
                result.name(),
                result.email()
        );
    }

    @GetMapping("/me/users")
    @Operation(summary = "View my supervised users")
    public List<SupervisedUserResponse> viewMySupervisedUsers(
            @AuthenticationPrincipal AuthenticatedAccount account
    ) {
        return viewMySupervisedUsersUseCase.viewMySupervisedUsers(account.id())
                .stream()
                .map(this::toSupervisedUserResponse)
                .toList();
    }

    private SupervisedUserResponse toSupervisedUserResponse(SupervisedUserResult result) {
        return new SupervisedUserResponse(
                result.id(),
                result.name(),
                result.email(),
                result.mode()
        );
    }
}