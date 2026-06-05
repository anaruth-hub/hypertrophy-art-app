package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.recovery;

import com.anaruth.hypertrophyartapp.domain.recovery.model.EnergyLevel;
import com.anaruth.hypertrophyartapp.domain.recovery.model.FatigueLevel;
import com.anaruth.hypertrophyartapp.domain.recovery.model.MuscleSorenessLevel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "recovery_checkins")
public class RecoveryCheckInDocument {

    @Id
    private String id;

    private String userId;

    private LocalDate date;

    private FatigueLevel fatigueLevel;

    private MuscleSorenessLevel sorenessLevel;

    private EnergyLevel energyLevel;

    private double sleepHours;

    private String notes;

    public RecoveryCheckInDocument() {
    }

    public RecoveryCheckInDocument(
            String id,
            String userId,
            LocalDate date,
            FatigueLevel fatigueLevel,
            MuscleSorenessLevel sorenessLevel,
            EnergyLevel energyLevel,
            double sleepHours,
            String notes
    ) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.fatigueLevel = fatigueLevel;
        this.sorenessLevel = sorenessLevel;
        this.energyLevel = energyLevel;
        this.sleepHours = sleepHours;
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

    public FatigueLevel getFatigueLevel() {
        return fatigueLevel;
    }

    public MuscleSorenessLevel getSorenessLevel() {
        return sorenessLevel;
    }

    public EnergyLevel getEnergyLevel() {
        return energyLevel;
    }

    public double getSleepHours() {
        return sleepHours;
    }

    public String getNotes() {
        return notes;
    }
}