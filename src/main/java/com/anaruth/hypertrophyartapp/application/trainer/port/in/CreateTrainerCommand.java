package com.anaruth.hypertrophyartapp.application.trainer.port.in;

public record CreateTrainerCommand(
        String name,
        String email
) {
}