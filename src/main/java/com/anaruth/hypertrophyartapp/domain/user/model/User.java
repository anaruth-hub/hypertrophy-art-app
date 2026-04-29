package com.anaruth.hypertrophyartapp.domain.user.model;

import java.util.Objects;

public class User {

    private final UserId id;
    private final String name;
    private final String email;
    private final UserMode mode;

    private User(UserId id, String name, String email, UserMode mode) {
        this.id = Objects.requireNonNull(id, "User id cannot be null");
        this.name = validateName(name);
        this.email = validateEmail(email);
        this.mode = Objects.requireNonNull(mode, "User mode cannot be null");
    }

    public static User create(String name, String email, UserMode mode) {
        return new User(UserId.newId(), name, email, mode);
    }

    public UserId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }

    public UserMode mode() {
        return mode;
    }

    private String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("User name cannot be blank");
        }
        return name.trim();
    }

    private String validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("User email cannot be blank");
        }
        return email.trim().toLowerCase();
    }
}