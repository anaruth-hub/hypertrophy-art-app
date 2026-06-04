package com.anaruth.hypertrophyartapp.application.user.port.out;

import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;
import com.anaruth.hypertrophyartapp.domain.user.model.User;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findByTrainerId(TrainerId trainerId);
    User save(User user);

    Optional<User> findById(UserId userId);
    Optional<User> findByEmail(String email);
}