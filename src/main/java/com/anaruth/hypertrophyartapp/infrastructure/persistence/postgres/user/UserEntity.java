package com.anaruth.hypertrophyartapp.infrastructure.persistence.postgres.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private String id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserModeEntity mode;

    private String trainerId;

    protected UserEntity() {}

    public UserEntity(String id, String name, String email, UserModeEntity mode, String trainerId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mode = mode;
        this.trainerId = trainerId;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public UserModeEntity getMode() { return mode; }
    public String getTrainerId() { return trainerId; }
}