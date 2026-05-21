package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.wellness;

import com.anaruth.hypertrophyartapp.domain.wellness.model.WellnessLevel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "wellness_checkins")
public class WellnessCheckInDocument {

    @Id
    private String id;

    private String userId;

    private LocalDate date;

    private WellnessLevel physicalState;
    private WellnessLevel mentalState;
    private WellnessLevel emotionalState;

    private WellnessLevel stressLevel;
    private WellnessLevel motivationLevel;

    private String notes;

    public WellnessCheckInDocument() {
    }

    public WellnessCheckInDocument(
            String id,
            String userId,
            LocalDate date,
            WellnessLevel physicalState,
            WellnessLevel mentalState,
            WellnessLevel emotionalState,
            WellnessLevel stressLevel,
            WellnessLevel motivationLevel,
            String notes
    ) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.physicalState = physicalState;
        this.mentalState = mentalState;
        this.emotionalState = emotionalState;
        this.stressLevel = stressLevel;
        this.motivationLevel = motivationLevel;
        this.notes = notes;
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

    public WellnessLevel getPhysicalState() {
        return physicalState;
    }

    public WellnessLevel getMentalState() {
        return mentalState;
    }

    public WellnessLevel getEmotionalState() {
        return emotionalState;
    }

    public WellnessLevel getStressLevel() {
        return stressLevel;
    }

    public WellnessLevel getMotivationLevel() {
        return motivationLevel;
    }

    public String getNotes() {
        return notes;
    }
}