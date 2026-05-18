package com.anaruth.hypertrophyartapp.application.nutrition.port.in;

public interface RegisterNutritionEntryUseCase {

    RegisterNutritionEntryResult register(RegisterNutritionEntryCommand command);
}