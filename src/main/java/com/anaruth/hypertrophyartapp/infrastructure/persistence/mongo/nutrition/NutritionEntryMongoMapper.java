package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.nutrition;

import com.anaruth.hypertrophyartapp.domain.nutrition.model.NutritionEntry;
import com.anaruth.hypertrophyartapp.domain.nutrition.model.NutritionEntryId;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;

import java.util.UUID;

public class NutritionEntryMongoMapper {

    public NutritionEntryDocument toDocument(NutritionEntry nutritionEntry) {
        return new NutritionEntryDocument(
                nutritionEntry.id().value().toString(),
                nutritionEntry.userId().value().toString(),
                nutritionEntry.date(),
                nutritionEntry.calories(),
                nutritionEntry.proteinGrams(),
                nutritionEntry.carbsGrams(),
                nutritionEntry.fatGrams(),
                nutritionEntry.hydrationLiters(),
                nutritionEntry.notes()
        );
    }

    public NutritionEntry toDomain(NutritionEntryDocument document) {
        return NutritionEntry.restore(
                NutritionEntryId.from(UUID.fromString(document.getId())),
                UserId.from(UUID.fromString(document.getUserId())),
                document.getDate(),
                document.getCalories(),
                document.getProteinGrams(),
                document.getCarbsGrams(),
                document.getFatGrams(),
                document.getHydrationLiters(),
                document.getNotes()
        );
    }
}