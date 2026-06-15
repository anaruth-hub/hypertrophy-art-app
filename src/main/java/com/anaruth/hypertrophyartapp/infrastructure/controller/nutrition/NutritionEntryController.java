package com.anaruth.hypertrophyartapp.infrastructure.controller.nutrition;

import com.anaruth.hypertrophyartapp.application.nutrition.port.in.RegisterNutritionEntryCommand;
import com.anaruth.hypertrophyartapp.application.nutrition.port.in.RegisterNutritionEntryResult;
import com.anaruth.hypertrophyartapp.application.nutrition.port.in.RegisterNutritionEntryUseCase;
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
@RequestMapping("/api/nutrition-entries")
@Tag(name = "Nutrition", description = "Nutrition and macros tracking")
public class NutritionEntryController {

    private final RegisterNutritionEntryUseCase registerNutritionEntryUseCase;

    public NutritionEntryController(RegisterNutritionEntryUseCase registerNutritionEntryUseCase) {
        this.registerNutritionEntryUseCase = registerNutritionEntryUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register nutrition macros")
    public NutritionEntryResponse register(
            @AuthenticationPrincipal AuthenticatedAccount account,
            @Valid @RequestBody RegisterNutritionEntryRequest request
    ) {
        requireRole(account, Role.USER);

        RegisterNutritionEntryResult result = registerNutritionEntryUseCase.register(
                new RegisterNutritionEntryCommand(
                        account.id(),
                        request.date(),
                        request.calories(),
                        request.proteinGrams(),
                        request.carbsGrams(),
                        request.fatGrams(),
                        request.hydrationLiters(),
                        request.notes()
                )
        );

        return new NutritionEntryResponse(
                result.id(),
                result.userId(),
                result.date(),
                result.calories(),
                result.proteinGrams(),
                result.carbsGrams(),
                result.fatGrams(),
                result.hydrationLiters(),
                result.notes()
        );
    }

    private void requireRole(AuthenticatedAccount account, Role role) {
        if (account == null || account.role() != role) {
            throw new AccessDeniedException("Required role: " + role);
        }
    }
}
