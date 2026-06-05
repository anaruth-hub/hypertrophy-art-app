package com.anaruth.hypertrophyartapp.domain.training.model;

import java.util.Objects;
import java.util.UUID;

public final class TrainingId {

    private final UUID value;

    private TrainingId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public static TrainingId newId() {
        return new TrainingId(UUID.randomUUID());
    }

    public static TrainingId from(UUID value) {
        return new TrainingId(value);
    }

    public UUID value() {
        return value;
    }
}