package com.anaruth.hypertrophyartapp.infrastructure.controller.trainer;

import com.anaruth.hypertrophyartapp.application.trainer.port.in.CreateTrainerCommand;
import com.anaruth.hypertrophyartapp.application.trainer.port.in.CreateTrainerResult;
import com.anaruth.hypertrophyartapp.application.trainer.port.in.CreateTrainerUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trainers")
@Tag(name = "Trainers", description = "Trainer profile management")
public class TrainerController {

    private final CreateTrainerUseCase createTrainerUseCase;

    public TrainerController(CreateTrainerUseCase createTrainerUseCase) {
        this.createTrainerUseCase = createTrainerUseCase;
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
}