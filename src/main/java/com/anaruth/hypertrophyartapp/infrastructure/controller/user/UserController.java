package com.anaruth.hypertrophyartapp.infrastructure.controller.user;

import com.anaruth.hypertrophyartapp.application.user.port.in.CreateUserCommand;
import com.anaruth.hypertrophyartapp.application.user.port.in.CreateUserResult;
import com.anaruth.hypertrophyartapp.application.user.port.in.CreateUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "User profile management")
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
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
}