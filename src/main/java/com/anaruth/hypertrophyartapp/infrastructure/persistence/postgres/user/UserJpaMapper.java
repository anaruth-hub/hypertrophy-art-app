package com.anaruth.hypertrophyartapp.infrastructure.persistence.postgres.user;

import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;
import com.anaruth.hypertrophyartapp.domain.user.model.User;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import com.anaruth.hypertrophyartapp.domain.user.model.UserMode;
import com.anaruth.hypertrophyartapp.infrastructure.persistence.postgres.trainer.TrainerEntity;

import java.util.UUID;

public class UserJpaMapper {

    public UserEntity toEntity(User user) {
        TrainerEntity trainerEntity = null;

        if (user.trainerId() != null) {
            trainerEntity = new TrainerEntity(
                    user.trainerId().value().toString(),
                    null,
                    null
            );
        }

        return new UserEntity(
                user.id().value().toString(),
                user.name(),
                user.email(),
                UserModeEntity.valueOf(user.mode().name()),
                trainerEntity
        );
    }

    public User toDomain(UserEntity entity) {
        TrainerId trainerId = entity.getTrainer() == null
                ? null
                : TrainerId.from(UUID.fromString(entity.getTrainer().getId()));

        return User.restore(
                UserId.from(UUID.fromString(entity.getId())),
                entity.getName(),
                entity.getEmail(),
                UserMode.valueOf(entity.getMode().name()),
                trainerId
        );
    }
}