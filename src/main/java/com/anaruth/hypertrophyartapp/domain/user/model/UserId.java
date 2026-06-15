package com.anaruth.hypertrophyartapp.domain.user.model;

import java.util.Objects;
import java.util.UUID;

public final class UserId {

    private final UUID value;

    private UserId(UUID value) {
        this.value = Objects.requireNonNull(value, "User id cannot be null");
    }

    public static UserId newId() {
         return new UserId(UUID.randomUUID());
    }

    public static UserId from(UUID value) {
          return new UserId(value);
    }

    public UUID value() {
          return value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof UserId userId)) {
            return false;
        }
        return value.equals(userId.value);
    }

    @Override
    public int hashCode() {
         return value.hashCode();
    }
}
