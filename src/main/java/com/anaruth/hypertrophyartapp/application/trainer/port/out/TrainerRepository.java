package com.anaruth.hypertrophyartapp.application.trainer.port.out;

import com.anaruth.hypertrophyartapp.domain.trainer.model.Trainer;

public interface TrainerRepository {

    Trainer save(Trainer trainer);
}