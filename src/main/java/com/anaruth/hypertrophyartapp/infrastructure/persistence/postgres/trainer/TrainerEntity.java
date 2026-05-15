package com.anaruth.hypertrophyartapp.infrastructure.persistence.postgres.trainer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trainers")
public class TrainerEntity {

    @Id
    private String id;

    private String name;
    private String email;

    public TrainerEntity() {}

    public TrainerEntity(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}