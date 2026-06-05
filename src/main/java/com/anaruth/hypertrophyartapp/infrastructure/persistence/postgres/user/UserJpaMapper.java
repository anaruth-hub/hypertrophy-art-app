package com.anaruth.hypertrophyartapp.infrastructure.persistence.postgres.user;

import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;
import com.anaruth.hypertrophyartapp.domain.user.model.User;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;

import java.util.UUID;

public class UserJpaMapper {

    public UserEntity toEntity(User user) {
        return new UserEntity(
                user.id().value().toString(),
                user.name(),
                user.email(),
                user.passwordHash(),
                user.role(),
                user.mode(),
                user.trainerId() == null ? null : user.trainerId().value().toString()
        );
    }

    public User toDomain(UserEntity entity) {
        return User.restore(
                UserId.from(UUID.fromString(entity.getId())),
                entity.getName(),
                entity.getEmail(),
                entity.getPasswordHash(),
                entity.getRole(),
                entity.getMode(),
                entity.getTrainerId() == null
                        ? null
                        : TrainerId.from(UUID.fromString(entity.getTrainerId()))
        );
    }
}