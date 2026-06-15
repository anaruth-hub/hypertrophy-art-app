package com.anaruth.hypertrophyartapp.infrastructure.controller.user;

import com.anaruth.hypertrophyartapp.application.user.port.in.AssignTrainerToUserCommand;
import com.anaruth.hypertrophyartapp.application.user.port.in.AssignTrainerToUserResult;
import com.anaruth.hypertrophyartapp.application.user.port.in.AssignTrainerToUserUseCase;
import com.anaruth.hypertrophyartapp.application.user.port.in.CreateUserCommand;
import com.anaruth.hypertrophyartapp.application.user.port.in.CreateUserResult;
import com.anaruth.hypertrophyartapp.application.user.port.in.CreateUserUseCase;
import com.anaruth.hypertrophyartapp.application.user.port.in.CurrentUserProfileResult;
import com.anaruth.hypertrophyartapp.application.user.port.in.GetCurrentUserProfileUseCase;
import com.anaruth.hypertrophyartapp.domain.auth.model.Role;
import com.anaruth.hypertrophyartapp.infrastructure.security.AuthenticatedAccount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "User profile management")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final AssignTrainerToUserUseCase assignTrainerToUserUseCase;
    private final GetCurrentUserProfileUseCase getCurrentUserProfileUseCase;

    public UserController(
            CreateUserUseCase createUserUseCase,
            AssignTrainerToUserUseCase assignTrainerToUserUseCase,
            GetCurrentUserProfileUseCase getCurrentUserProfileUseCase
    ) {
        this.createUserUseCase = createUserUseCase;
        this.assignTrainerToUserUseCase = assignTrainerToUserUseCase;
        this.getCurrentUserProfileUseCase = getCurrentUserProfileUseCase;
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
            @AuthenticationPrincipal AuthenticatedAccount account,
            @PathVariable UUID userId,
            @PathVariable UUID trainerId
    ) {
        requireRole(account, Role.USER);
        if (!account.id().equals(userId)) {
            throw new AccessDeniedException("Users can only assign their own trainer");
        }

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

    @PostMapping("/me/assign-trainer/{trainerId}")
    @Operation(summary = "Assign trainer to current authenticated supervised user")
    public AssignTrainerResponse assignTrainerToCurrentUser(
            @AuthenticationPrincipal AuthenticatedAccount account,
            @PathVariable UUID trainerId
    ) {
        requireRole(account, Role.USER);

        AssignTrainerToUserResult result = assignTrainerToUserUseCase.assignTrainer(
                new AssignTrainerToUserCommand(account.id(), trainerId)
        );

        return new AssignTrainerResponse(
                result.userId(),
                result.userName(),
                result.mode(),
                result.trainerId()
        );
    }

    @GetMapping("/me")
    @Operation(summary = "Get current authenticated user profile")
    public CurrentUserProfileResponse getCurrentUserProfile(
            @AuthenticationPrincipal AuthenticatedAccount account
    ) {
        requireRole(account, Role.USER);

        CurrentUserProfileResult result =
                getCurrentUserProfileUseCase.getCurrentUserProfile(account.id());

        return new CurrentUserProfileResponse(
                result.id(),
                result.name(),
                result.email(),
                result.role(),
                result.mode()
        );
    }

    private void requireRole(AuthenticatedAccount account, Role role) {
        if (account == null || account.role() != role) {
            throw new AccessDeniedException("Required role: " + role);
        }
    }
}
