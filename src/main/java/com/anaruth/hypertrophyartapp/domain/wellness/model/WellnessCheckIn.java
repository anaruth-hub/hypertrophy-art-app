package com.anaruth.hypertrophyartapp.domain.wellness.model;

import com.anaruth.hypertrophyartapp.domain.user.model.UserId;

import java.time.LocalDate;
import java.util.Objects;

public class WellnessCheckIn {

    private final WellnessCheckInId id;
    private final UserId userId;
    private final LocalDate date;

    private final WellnessLevel physicalState;
    private final WellnessLevel mentalState;
    private final WellnessLevel emotionalState;

    private final WellnessLevel stressLevel;
    private final WellnessLevel motivationLevel;

    private final String notes;

    private WellnessCheckIn(
            WellnessCheckInId id,
            UserId userId,
            LocalDate date,
            WellnessLevel physicalState,
            WellnessLevel mentalState,
            WellnessLevel emotionalState,
            WellnessLevel stressLevel,
            WellnessLevel motivationLevel,
            String notes
    ) {
        this.id = Objects.requireNonNull(id);
        this.userId = Objects.requireNonNull(userId);
        this.date = Objects.requireNonNull(date);

        this.physicalState = Objects.requireNonNull(physicalState);
        this.mentalState = Objects.requireNonNull(mentalState);
        this.emotionalState = Objects.requireNonNull(emotionalState);

        this.stressLevel = Objects.requireNonNull(stressLevel);
        this.motivationLevel = Objects.requireNonNull(motivationLevel);

        this.notes = notes == null ? "" : notes.trim();
    }

    public static WellnessCheckIn create(
            UserId userId,
            LocalDate date,
            WellnessLevel physicalState,
            WellnessLevel mentalState,
            WellnessLevel emotionalState,
            WellnessLevel stressLevel,
            WellnessLevel motivationLevel,
            String notes
    ) {
        return new WellnessCheckIn(
                WellnessCheckInId.newId(),
                userId,
                date,
                physicalState,
                mentalState,
                emotionalState,
                stressLevel,
                motivationLevel,
                notes
        );
    }

    public static WellnessCheckIn restore(
            WellnessCheckInId id,
            UserId userId,
            LocalDate date,
            WellnessLevel physicalState,
            WellnessLevel mentalState,
            WellnessLevel emotionalState,
            WellnessLevel stressLevel,
            WellnessLevel motivationLevel,
            String notes
    ) {
        return new WellnessCheckIn(
                id,
                userId,
                date,
                physicalState,
                mentalState,
                emotionalState,
                stressLevel,
                motivationLevel,
                notes
        );
    }

    public WellnessCheckInId id() {
        return id;
    }

    public UserId userId() {
        return userId;
    }

    public LocalDate date() {
        return date;
    }

    public WellnessLevel physicalState() {
        return physicalState;
    }

    public WellnessLevel mentalState() {
        return mentalState;
    }

    public WellnessLevel emotionalState() {
        return emotionalState;
    }

    public WellnessLevel stressLevel() {
        return stressLevel;
    }

    public WellnessLevel motivationLevel() {
        return motivationLevel;
    }

    public String notes() {
        return notes;
    }
}