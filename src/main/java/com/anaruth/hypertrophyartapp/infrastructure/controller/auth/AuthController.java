package com.anaruth.hypertrophyartapp.infrastructure.controller.auth;

import com.anaruth.hypertrophyartapp.application.auth.port.in.AuthResult;
import com.anaruth.hypertrophyartapp.application.auth.port.in.RegisterTrainerAuthCommand;
import com.anaruth.hypertrophyartapp.application.auth.port.in.RegisterTrainerAuthUseCase;
import com.anaruth.hypertrophyartapp.application.auth.port.in.RegisterUserAuthCommand;
import com.anaruth.hypertrophyartapp.application.auth.port.in.RegisterUserAuthUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "User and trainer authentication")
public class AuthController {

    private final RegisterUserAuthUseCase registerUserAuthUseCase;
    private final RegisterTrainerAuthUseCase registerTrainerAuthUseCase;

    public AuthController(
            RegisterUserAuthUseCase registerUserAuthUseCase,
            RegisterTrainerAuthUseCase registerTrainerAuthUseCase
    ) {
        this.registerUserAuthUseCase = registerUserAuthUseCase;
        this.registerTrainerAuthUseCase = registerTrainerAuthUseCase;
    }

    @PostMapping("/register-user")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register user with password")
    public AuthResponse registerUser(@RequestBody RegisterUserRequest request) {
        AuthResult result = registerUserAuthUseCase.registerUser(
                new RegisterUserAuthCommand(
                        request.name(),
                        request.email(),
                        request.password(),
                        request.mode()
                )
        );

        return toResponse(result);
    }

    @PostMapping("/register-trainer")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register trainer with password")
    public AuthResponse registerTrainer(@RequestBody RegisterTrainerRequest request) {
        AuthResult result = registerTrainerAuthUseCase.registerTrainer(
                new RegisterTrainerAuthCommand(
                        request.name(),
                        request.email(),
                        request.password()
                )
        );

        return toResponse(result);
    }

    private AuthResponse toResponse(AuthResult result) {
        return new AuthResponse(
                result.token(),
                result.role(),
                result.id(),
                result.name(),
                result.email()
        );
    }
}