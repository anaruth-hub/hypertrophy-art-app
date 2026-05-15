package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.training;

import com.anaruth.hypertrophyartapp.domain.training.model.TrainingIntensity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "trainings")
public class TrainingDocument {

    @Id
    private String id;

    private String userId;
    private LocalDate date;
    private String muscleGroup;
    private String exercises;
    private TrainingIntensity intensity;
    private int durationMinutes;

    public TrainingDocument() {
    }

    public TrainingDocument(
            String id,
            String userId,
            LocalDate date,
            String muscleGroup,
            String exercises,
            TrainingIntensity intensity,
            int durationMinutes
    ) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.muscleGroup = muscleGroup;
        this.exercises = exercises;
        this.intensity = intensity;
        this.durationMinutes = durationMinutes;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public String getExercises() {
        return exercises;
    }

    public TrainingIntensity getIntensity() {
        return intensity;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }
}