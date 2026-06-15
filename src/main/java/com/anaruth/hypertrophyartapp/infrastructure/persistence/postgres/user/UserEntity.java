package com.anaruth.hypertrophyartapp.infrastructure.persistence.postgres.user;

import com.anaruth.hypertrophyartapp.domain.auth.model.Role;
import com.anaruth.hypertrophyartapp.domain.user.model.UserMode;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserMode mode;

    private String trainerId;

    protected UserEntity() {
    }

    public UserEntity(
            String id,
            String name,
            String email,
            String passwordHash,
            Role role,
            UserMode mode,
            String trainerId
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.mode = mode;
        this.trainerId = trainerId;
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

    public UserMode getMode() {
        return mode;
    }

    public String getTrainerId() {
        return trainerId;
    }
}
