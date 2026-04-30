package com.anaruth.hypertrophyartapp.infrastructure.controller.trainer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateTrainerRequest(
        @NotBlank String name,
        @Email @NotBlank String email
) {
}