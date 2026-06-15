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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof TrainerId trainerId)) {
            return false;
        }
        return value.equals(trainerId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
