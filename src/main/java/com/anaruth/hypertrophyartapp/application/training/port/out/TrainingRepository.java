package com.anaruth.hypertrophyartapp.application.training.port.out;

import com.anaruth.hypertrophyartapp.domain.training.model.Training;

public interface TrainingRepository {

    Training save(Training training);
}