package com.anaruth.hypertrophyartapp.application.nutrition.service;

import com.anaruth.hypertrophyartapp.application.nutrition.port.in.RegisterNutritionEntryCommand;
import com.anaruth.hypertrophyartapp.application.nutrition.port.in.RegisterNutritionEntryResult;
import com.anaruth.hypertrophyartapp.application.nutrition.port.in.RegisterNutritionEntryUseCase;
import com.anaruth.hypertrophyartapp.application.nutrition.port.out.NutritionEntryRepository;
import com.anaruth.hypertrophyartapp.application.user.port.out.UserRepository;
import com.anaruth.hypertrophyartapp.domain.nutrition.model.NutritionEntry;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import org.springframework.stereotype.Service;

@Service
public class RegisterNutritionEntryService implements RegisterNutritionEntryUseCase {

    private final NutritionEntryRepository nutritionEntryRepository;
    private final UserRepository userRepository;

    public RegisterNutritionEntryService(
            NutritionEntryRepository nutritionEntryRepository,
            UserRepository userRepository
    ) {
        this.nutritionEntryRepository = nutritionEntryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RegisterNutritionEntryResult register(RegisterNutritionEntryCommand command) {
        UserId userId = UserId.from(command.userId());

        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        NutritionEntry nutritionEntry = NutritionEntry.create(
                userId,
                command.date(),
                command.calories(),
                command.proteinGrams(),
                command.carbsGrams(),
                command.fatGrams(),
                command.hydrationLiters(),
                command.notes()
        );

        NutritionEntry saved = nutritionEntryRepository.save(nutritionEntry);

        return new RegisterNutritionEntryResult(
                saved.id().value(),
                saved.userId().value(),
                saved.date(),
                saved.calories(),
                saved.proteinGrams(),
                saved.carbsGrams(),
                saved.fatGrams(),
                saved.hydrationLiters(),
                saved.notes()
        );
    }
}