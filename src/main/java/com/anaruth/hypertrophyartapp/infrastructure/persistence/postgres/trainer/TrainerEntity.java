package com.anaruth.hypertrophyartapp.infrastructure.persistence.postgres.trainer;

import com.anaruth.hypertrophyartapp.domain.auth.model.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "trainers")
public class TrainerEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    protected TrainerEntity() {
    }

    public TrainerEntity(
            String id,
            String name,
            String email,
            String passwordHash,
            Role role
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public Role getRole() {
        return role;
    }
}
