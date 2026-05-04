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

    public UUID value() {
        return value;
    }
}