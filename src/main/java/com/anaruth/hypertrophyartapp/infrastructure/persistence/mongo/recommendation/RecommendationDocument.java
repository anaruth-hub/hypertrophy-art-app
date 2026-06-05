package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.recommendation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "recommendations")
public class RecommendationDocument {

    @Id
    private String id;

    private String trainerId;
    private String userId;
    private LocalDate date;
    private String message;

    public RecommendationDocument() {
    }

    public RecommendationDocument(
            String id,
            String trainerId,
            String userId,
            LocalDate date,
            String message
    ) {
        this.id = id;
        this.trainerId = trainerId;
        this.userId = userId;
        this.date = date;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}