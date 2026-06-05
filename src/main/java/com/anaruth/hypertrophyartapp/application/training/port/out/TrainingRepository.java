package com.anaruth.hypertrophyartapp.application.training.port.out;

import com.anaruth.hypertrophyartapp.domain.training.model.Training;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import java.util.List;

public interface TrainingRepository {
    List<Training> findByUserId(UserId userId);
    Training save(Training training);
}