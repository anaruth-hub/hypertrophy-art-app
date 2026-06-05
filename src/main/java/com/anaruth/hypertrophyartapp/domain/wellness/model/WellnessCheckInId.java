package com.anaruth.hypertrophyartapp.domain.wellness.model;

import java.util.Objects;
import java.util.UUID;

public final class WellnessCheckInId {

    private final UUID value;

    private WellnessCheckInId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public static WellnessCheckInId newId() {
        return new WellnessCheckInId(UUID.randomUUID());
    }

    public static WellnessCheckInId from(UUID value) {
        return new WellnessCheckInId(value);
    }

    public UUID value() {
        return value;
    }
}