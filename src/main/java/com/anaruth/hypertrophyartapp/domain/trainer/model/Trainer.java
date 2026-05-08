package com.anaruth.hypertrophyartapp.domain.trainer.model;

import java.util.Objects;

public class Trainer {

    private final TrainerId id;
    private final String name;
    private final String email;

    private Trainer(TrainerId id, String name, String email) {
        this.id = Objects.requireNonNull(id);
        this.name = validateName(name);
        this.email = validateEmail(email);
    }

    public static Trainer create(String name, String email) {
        return new Trainer(TrainerId.newId(), name, email);
    }

    public static Trainer restore(TrainerId id, String name, String email) {
        return new Trainer(id, name, email);
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
}