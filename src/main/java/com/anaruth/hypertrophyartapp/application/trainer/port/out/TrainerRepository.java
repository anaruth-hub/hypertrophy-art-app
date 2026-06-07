package com.anaruth.hypertrophyartapp.application.trainer.port.out;

import com.anaruth.hypertrophyartapp.domain.trainer.model.Trainer;
import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;

import java.util.List;
import java.util.Optional;

public interface TrainerRepository {

    Trainer save(Trainer trainer);

    Optional<Trainer> findById(TrainerId trainerId);

    Optional<Trainer> findByEmail(String email);

    List<Trainer> findAll();
}