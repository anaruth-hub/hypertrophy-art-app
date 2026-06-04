package com.anaruth.hypertrophyartapp.infrastructure.persistence.postgres.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataUserJpaRepository extends JpaRepository<UserEntity, String> {

    List<UserEntity> findByTrainerId(String trainerId);
    Optional<UserEntity> findByEmail(String email);
}