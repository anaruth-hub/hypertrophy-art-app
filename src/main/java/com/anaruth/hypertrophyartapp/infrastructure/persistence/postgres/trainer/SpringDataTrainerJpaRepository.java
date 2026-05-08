package com.anaruth.hypertrophyartapp.infrastructure.persistence.postgres.trainer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTrainerJpaRepository
        extends JpaRepository<TrainerEntity, String> {
}