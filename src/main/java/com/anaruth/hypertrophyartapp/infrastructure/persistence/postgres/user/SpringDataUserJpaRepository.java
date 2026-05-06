package com.anaruth.hypertrophyartapp.infrastructure.persistence.postgres.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserJpaRepository extends JpaRepository<UserEntity, String> {
}