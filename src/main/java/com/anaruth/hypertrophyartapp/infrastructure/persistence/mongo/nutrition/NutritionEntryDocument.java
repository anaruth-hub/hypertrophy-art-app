package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.nutrition;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "nutrition_entries")
public class NutritionEntryDocument {

    @Id
    private String id;

    private String userId;
    private LocalDate date;
    private int calories;
    private double proteinGrams;
    private double carbsGrams;
    private double fatGrams;
    private double hydrationLiters;
    private String notes;

    public NutritionEntryDocument() {
    }

    public NutritionEntryDocument(
            String id,
            String userId,
            LocalDate date,
            int calories,
            double proteinGrams,
            double carbsGrams,
            double fatGrams,
            double hydrationLiters,
            String notes
    ) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.calories = calories;
        this.proteinGrams = proteinGrams;
        this.carbsGrams = carbsGrams;
        this.fatGrams = fatGrams;
        this.hydrationLiters = hydrationLiters;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getCalories() {
        return calories;
    }

    public double getProteinGrams() {
        return proteinGrams;
    }

    public double getCarbsGrams() {
        return carbsGrams;
    }

    public double getFatGrams() {
        return fatGrams;
    }

    public double getHydrationLiters() {
        return hydrationLiters;
    }

    public String getNotes() {
        return notes;
    }
}