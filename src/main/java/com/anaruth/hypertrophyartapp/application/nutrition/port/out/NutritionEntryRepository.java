package com.anaruth.hypertrophyartapp.application.nutrition.port.out;

import com.anaruth.hypertrophyartapp.domain.nutrition.model.NutritionEntry;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import java.util.List;

public interface NutritionEntryRepository {
    List<NutritionEntry> findByUserId(UserId userId);
    NutritionEntry save(NutritionEntry nutritionEntry);
}