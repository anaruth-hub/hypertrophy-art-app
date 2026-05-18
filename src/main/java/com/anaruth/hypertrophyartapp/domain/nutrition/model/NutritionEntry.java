package com.anaruth.hypertrophyartapp.domain.nutrition.model;

import com.anaruth.hypertrophyartapp.domain.user.model.UserId;

import java.time.LocalDate;
import java.util.Objects;

public class NutritionEntry {

    private final NutritionEntryId id;
    private final UserId userId;
    private final LocalDate date;
    private final int calories;
    private final double proteinGrams;
    private final double carbsGrams;
    private final double fatGrams;
    private final double hydrationLiters;
    private final String notes;

    private NutritionEntry(
            NutritionEntryId id,
            UserId userId,
            LocalDate date,
            int calories,
            double proteinGrams,
            double carbsGrams,
            double fatGrams,
            double hydrationLiters,
            String notes
    ) {
        this.id = Objects.requireNonNull(id);
        this.userId = Objects.requireNonNull(userId);
        this.date = Objects.requireNonNull(date);
        this.calories = validateCalories(calories);
        this.proteinGrams = validateNonNegative(proteinGrams, "protein");
        this.carbsGrams = validateNonNegative(carbsGrams, "carbs");
        this.fatGrams = validateNonNegative(fatGrams, "fat");
        this.hydrationLiters = validateNonNegative(hydrationLiters, "hydration");
        this.notes = notes == null ? "" : notes.trim();
    }

    public static NutritionEntry create(
            UserId userId,
            LocalDate date,
            int calories,
            double proteinGrams,
            double carbsGrams,
            double fatGrams,
            double hydrationLiters,
            String notes
    ) {
        return new NutritionEntry(
                NutritionEntryId.newId(),
                userId,
                date,
                calories,
                proteinGrams,
                carbsGrams,
                fatGrams,
                hydrationLiters,
                notes
        );
    }

    public static NutritionEntry restore(
            NutritionEntryId id,
            UserId userId,
            LocalDate date,
            int calories,
            double proteinGrams,
            double carbsGrams,
            double fatGrams,
            double hydrationLiters,
            String notes
    ) {
        return new NutritionEntry(
                id,
                userId,
                date,
                calories,
                proteinGrams,
                carbsGrams,
                fatGrams,
                hydrationLiters,
                notes
        );
    }

    private int validateCalories(int calories) {
        if (calories < 0) {
            throw new IllegalArgumentException("Calories cannot be negative");
        }
        return calories;
    }

    private double validateNonNegative(double value, String field) {
        if (value < 0) {
            throw new IllegalArgumentException(field + " cannot be negative");
        }
        return value;
    }

    public NutritionEntryId id() {
        return id;
    }

    public UserId userId() {
        return userId;
    }

    public LocalDate date() {
        return date;
    }

    public int calories() {
        return calories;
    }

    public double proteinGrams() {
        return proteinGrams;
    }

    public double carbsGrams() {
        return carbsGrams;
    }

    public double fatGrams() {
        return fatGrams;
    }

    public double hydrationLiters() {
        return hydrationLiters;
    }

    public String notes() {
        return notes;
    }
}