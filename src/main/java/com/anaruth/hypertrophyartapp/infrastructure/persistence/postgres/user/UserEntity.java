package com.anaruth.hypertrophyartapp.infrastructure.persistence.postgres.user;

import com.anaruth.hypertrophyartapp.infrastructure.persistence.postgres.trainer.TrainerEntity;
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

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private TrainerEntity trainer;

    protected UserEntity() {
    }

    public UserEntity(
            String id,
            String name,
            String email,
            UserModeEntity mode,
            TrainerEntity trainer
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mode = mode;
        this.trainer = trainer;
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

    public UserModeEntity getMode() {
        return mode;
    }

    public TrainerEntity getTrainer() {
        return trainer;
    }
}