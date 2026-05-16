package com.anaruth.hypertrophyartapp.domain.recovery.model;

import java.util.Objects;
import java.util.UUID;

public final class RecoveryCheckInId {

    private final UUID value;

    private RecoveryCheckInId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public static RecoveryCheckInId newId() {
        return new RecoveryCheckInId(UUID.randomUUID());
    }

    public static RecoveryCheckInId from(UUID value) {
        return new RecoveryCheckInId(value);
    }

    public UUID value() {
        return value;
    }
}