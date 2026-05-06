package com.anaruth.hypertrophyartapp.infrastructure.persistence.postgres.user;

import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;
import com.anaruth.hypertrophyartapp.domain.user.model.User;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import com.anaruth.hypertrophyartapp.domain.user.model.UserMode;

import java.util.UUID;

public class UserJpaMapper {

    public UserEntity toEntity(User user) {
        return new UserEntity(
                user.id().value().toString(),
                user.name(),
                user.email(),
                UserModeEntity.valueOf(user.mode().name()),
                user.trainerId() == null ? null : user.trainerId().value().toString()
        );
    }

    public User toDomain(UserEntity entity) {
        TrainerId trainerId = entity.getTrainerId() == null
                ? null
                : TrainerId.from(UUID.fromString(entity.getTrainerId()));

        return User.restore(
                UserId.from(UUID.fromString(entity.getId())),
                entity.getName(),
                entity.getEmail(),
                UserMode.valueOf(entity.getMode().name()),
                trainerId
        );
    }
}