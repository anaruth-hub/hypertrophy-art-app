package com.anaruth.hypertrophyartapp.infrastructure.controller.user;

import com.anaruth.hypertrophyartapp.application.user.port.in.AssignTrainerToUserCommand;
import com.anaruth.hypertrophyartapp.application.user.port.in.AssignTrainerToUserResult;
import com.anaruth.hypertrophyartapp.application.user.port.in.AssignTrainerToUserUseCase;
import com.anaruth.hypertrophyartapp.application.user.port.in.CreateUserCommand;
import com.anaruth.hypertrophyartapp.application.user.port.in.CreateUserResult;
import com.anaruth.hypertrophyartapp.application.user.port.in.CreateUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "User profile management")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final AssignTrainerToUserUseCase assignTrainerToUserUseCase;

    public UserController(
            CreateUserUseCase createUserUseCase,
            AssignTrainerToUserUseCase assignTrainerToUserUseCase
    ) {
        this.createUserUseCase = createUserUseCase;
        this.assignTrainerToUserUseCase = assignTrainerToUserUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create user profile")
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        CreateUserResult result = createUserUseCase.createUser(
                new CreateUserCommand(
                        request.name(),
                        request.email(),
                        request.mode()
                )
        );

        return new UserResponse(
                result.id(),
                result.name(),
                result.email(),
                result.mode()
        );
    }

    @PostMapping("/{userId}/assign-trainer/{trainerId}")
    @Operation(summary = "Assign trainer to supervised user")
    public AssignTrainerResponse assignTrainerToUser(
            @PathVariable UUID userId,
            @PathVariable UUID trainerId
    ) {
        AssignTrainerToUserResult result = assignTrainerToUserUseCase.assignTrainer(
                new AssignTrainerToUserCommand(userId, trainerId)
        );

        return new AssignTrainerResponse(
                result.userId(),
                result.userName(),
                result.mode(),
                result.trainerId()
        );
    }
}