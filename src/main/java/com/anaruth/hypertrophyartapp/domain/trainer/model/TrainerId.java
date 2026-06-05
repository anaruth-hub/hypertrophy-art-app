package com.anaruth.hypertrophyartapp.domain.trainer.model;

import java.util.Objects;
import java.util.UUID;

public final class TrainerId {

    private final UUID value;

    private TrainerId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public static TrainerId newId() {
        return new TrainerId(UUID.randomUUID());
    }

    public static TrainerId from(UUID value) {
        return new TrainerId(value);
    }

    public UUID value() {
        return value;
    }
}