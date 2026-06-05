package com.anaruth.hypertrophyartapp.domain.trainer.model;

import com.anaruth.hypertrophyartapp.domain.auth.model.Role;

import java.util.Objects;

public class Trainer {

    private final TrainerId id;
    private final String name;
    private final String email;
    private final String passwordHash;
    private final Role role;

    private Trainer(TrainerId id, String name, String email, String passwordHash, Role role) {
        this.id = Objects.requireNonNull(id);
        this.name = validateName(name);
        this.email = validateEmail(email);
        this.passwordHash = validatePasswordHash(passwordHash);
        this.role = Objects.requireNonNull(role, "Trainer role cannot be null");
    }

    public static Trainer create(String name, String email, String passwordHash) {
        return new Trainer(
                TrainerId.newId(),
                name,
                email,
                passwordHash,
                Role.TRAINER
        );
    }

    public static Trainer restore(
            TrainerId id,
            String name,
            String email,
            String passwordHash,
            Role role
    ) {
        return new Trainer(
                id,
                name,
                email,
                passwordHash,
                role
        );
    }

    public TrainerId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }

    public String passwordHash() {
        return passwordHash;
    }

    public Role role() {
        return role;
    }

    private String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Trainer name cannot be blank");
        }
        return name.trim();
    }

    private String validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Trainer email cannot be blank");
        }
        return email.trim().toLowerCase();
    }

    private String validatePasswordHash(String passwordHash) {
        if (passwordHash == null || passwordHash.isBlank()) {
            throw new IllegalArgumentException("Trainer password hash cannot be blank");
        }
        return passwordHash;
    }
}