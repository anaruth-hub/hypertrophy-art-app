package com.anaruth.hypertrophyartapp.domain.nutrition.model;

import java.util.Objects;
import java.util.UUID;

public final class NutritionEntryId {

    private final UUID value;

    private NutritionEntryId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public static NutritionEntryId newId() {
        return new NutritionEntryId(UUID.randomUUID());
    }

    public static NutritionEntryId from(UUID value) {
        return new NutritionEntryId(value);
    }

    public UUID value() {
        return value;
    }
}