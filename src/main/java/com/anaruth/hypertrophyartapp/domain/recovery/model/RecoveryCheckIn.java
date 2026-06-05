package com.anaruth.hypertrophyartapp.domain.recovery.model;

import com.anaruth.hypertrophyartapp.domain.user.model.UserId;

import java.time.LocalDate;
import java.util.Objects;

public class RecoveryCheckIn {

    private final RecoveryCheckInId id;
    private final UserId userId;
    private final LocalDate date;
    private final FatigueLevel fatigueLevel;
    private final MuscleSorenessLevel sorenessLevel;
    private final EnergyLevel energyLevel;
    private final double sleepHours;
    private final String notes;

    private RecoveryCheckIn(
            RecoveryCheckInId id,
            UserId userId,
            LocalDate date,
            FatigueLevel fatigueLevel,
            MuscleSorenessLevel sorenessLevel,
            EnergyLevel energyLevel,
            double sleepHours,
            String notes
    ) {

        this.id = Objects.requireNonNull(id);
        this.userId = Objects.requireNonNull(userId);
        this.date = Objects.requireNonNull(date);
        this.fatigueLevel = Objects.requireNonNull(fatigueLevel);
        this.sorenessLevel = Objects.requireNonNull(sorenessLevel);
        this.energyLevel = Objects.requireNonNull(energyLevel);

        validateSleepHours(sleepHours);

        this.sleepHours = sleepHours;
        this.notes = notes == null ? "" : notes.trim();
    }

    public static RecoveryCheckIn create(
            UserId userId,
            LocalDate date,
            FatigueLevel fatigueLevel,
            MuscleSorenessLevel sorenessLevel,
            EnergyLevel energyLevel,
            double sleepHours,
            String notes
    ) {

        return new RecoveryCheckIn(
                RecoveryCheckInId.newId(),
                userId,
                date,
                fatigueLevel,
                sorenessLevel,
                energyLevel,
                sleepHours,
                notes
        );
    }

    public static RecoveryCheckIn restore(
            RecoveryCheckInId id,
            UserId userId,
            LocalDate date,
            FatigueLevel fatigueLevel,
            MuscleSorenessLevel sorenessLevel,
            EnergyLevel energyLevel,
            double sleepHours,
            String notes
    ) {

        return new RecoveryCheckIn(
                id,
                userId,
                date,
                fatigueLevel,
                sorenessLevel,
                energyLevel,
                sleepHours,
                notes
        );
    }

    private void validateSleepHours(double sleepHours) {
        if (sleepHours < 0 || sleepHours > 24) {
            throw new IllegalArgumentException(
                    "Sleep hours must be between 0 and 24"
            );
        }
    }

    public RecoveryCheckInId id() {
        return id;
    }

    public UserId userId() {
        return userId;
    }

    public LocalDate date() {
        return date;
    }

    public FatigueLevel fatigueLevel() {
        return fatigueLevel;
    }

    public MuscleSorenessLevel sorenessLevel() {
        return sorenessLevel;
    }

    public EnergyLevel energyLevel() {
        return energyLevel;
    }

    public double sleepHours() {
        return sleepHours;
    }

    public String notes() {
        return notes;
    }
}