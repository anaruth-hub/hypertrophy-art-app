package com.anaruth.hypertrophyartapp.domain.training.model;

import com.anaruth.hypertrophyartapp.domain.user.model.UserId;

import java.time.LocalDate;
import java.util.Objects;

public class Training {

    private final TrainingId id;
    private final UserId userId;
    private final LocalDate date;
    private final String muscleGroup;
    private final String exercises;
    private final TrainingIntensity intensity;
    private final int durationMinutes;

    private Training(
            TrainingId id,
            UserId userId,
            LocalDate date,
            String muscleGroup,
            String exercises,
            TrainingIntensity intensity,
            int durationMinutes
    ) {
        this.id = Objects.requireNonNull(id);
        this.userId = Objects.requireNonNull(userId);
        this.date = Objects.requireNonNull(date);
        this.muscleGroup = validateText(muscleGroup, "muscle group");
        this.exercises = validateText(exercises, "exercises");
        this.intensity = Objects.requireNonNull(intensity);
        this.durationMinutes = validateDuration(durationMinutes);
    }

    public static Training create(
            UserId userId,
            LocalDate date,
            String muscleGroup,
            String exercises,
            TrainingIntensity intensity,
            int durationMinutes
    ) {
        return new Training(
                TrainingId.newId(),
                userId,
                date,
                muscleGroup,
                exercises,
                intensity,
                durationMinutes
        );
    }

    public TrainingId id() { return id; }
    public UserId userId() { return userId; }
    public LocalDate date() { return date; }
    public String muscleGroup() { return muscleGroup; }
    public String exercises() { return exercises; }
    public TrainingIntensity intensity() { return intensity; }
    public int durationMinutes() { return durationMinutes; }

    private String validateText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " cannot be blank");
        }
        return value.trim();
    }

    private int validateDuration(int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0");
        }
        return minutes;
    }
}