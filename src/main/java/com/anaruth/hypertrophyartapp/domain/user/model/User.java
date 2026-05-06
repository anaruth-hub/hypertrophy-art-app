package com.anaruth.hypertrophyartapp.domain.user.model;

import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;

import java.util.Objects;

public class User {

    private final UserId id;
    private final String name;
    private final String email;
    private final UserMode mode;
    private TrainerId trainerId;

    private User(UserId id, String name, String email, UserMode mode) {
        this.id = Objects.requireNonNull(id, "User id cannot be null");
        this.name = validateName(name);
        this.email = validateEmail(email);
        this.mode = Objects.requireNonNull(mode, "User mode cannot be null");
    }

    public static User create(String name, String email, UserMode mode) {
        return new User(UserId.newId(), name, email, mode);
    }

    public static User restore(UserId id, String name, String email, UserMode mode, TrainerId trainerId) {
        User user = new User(id, name, email, mode);
        user.trainerId = trainerId;
        return user;
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

    public TrainerId trainerId() {
        return trainerId;
    }

    public void assignTrainer(TrainerId trainerId) {
        if (this.mode != UserMode.SUPERVISED) {
            throw new IllegalStateException("Only supervised users can have a trainer");
        }
        this.trainerId = trainerId;
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