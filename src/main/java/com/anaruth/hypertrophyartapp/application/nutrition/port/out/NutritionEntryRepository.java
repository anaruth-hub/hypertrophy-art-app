package com.anaruth.hypertrophyartapp.application.nutrition.port.out;

import com.anaruth.hypertrophyartapp.domain.nutrition.model.NutritionEntry;

public interface NutritionEntryRepository {

    NutritionEntry save(NutritionEntry nutritionEntry);
}