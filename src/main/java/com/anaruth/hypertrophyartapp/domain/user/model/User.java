package com.anaruth.hypertrophyartapp.domain.user.model;

import com.anaruth.hypertrophyartapp.domain.auth.model.Role;
import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;

import java.util.Objects;

public class User {

    private final UserId id;
    private final String name;
    private final String email;
    private final String passwordHash;
    private final Role role;
    private final UserMode mode;
    private TrainerId trainerId;

    private User(UserId id, String name, String email, String passwordHash, Role role, UserMode mode) {
        this.id = Objects.requireNonNull(id, "User id cannot be null");
        this.name = validateName(name);
        this.email = validateEmail(email);
        this.passwordHash = validatePasswordHash(passwordHash);
        this.role = Objects.requireNonNull(role, "User role cannot be null");
        this.mode = Objects.requireNonNull(mode, "User mode cannot be null");
    }

    public static User create(String name, String email, String passwordHash, UserMode mode) {
        return new User(UserId.newId(), name, email, passwordHash, Role.USER, mode);
    }

    public static User restore(
            UserId id,
            String name,
            String email,
            String passwordHash,
            Role role,
            UserMode mode,
            TrainerId trainerId
    ) {
        User user = new User(id, name, email, passwordHash, role, mode);
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

    public String passwordHash() {
        return passwordHash;
    }

    public Role role() {
        return role;
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

    private String validatePasswordHash(String passwordHash) {
        if (passwordHash == null || passwordHash.isBlank()) {
            throw new IllegalArgumentException("User password hash cannot be blank");
        }
        return passwordHash;
    }
}