package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.recovery;

import com.anaruth.hypertrophyartapp.domain.recovery.model.RecoveryCheckIn;
import com.anaruth.hypertrophyartapp.domain.recovery.model.RecoveryCheckInId;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;

import java.util.UUID;

public class RecoveryCheckInMongoMapper {

    public RecoveryCheckInDocument toDocument(RecoveryCheckIn recoveryCheckIn) {

        return new RecoveryCheckInDocument(
                recoveryCheckIn.id().value().toString(),
                recoveryCheckIn.userId().value().toString(),
                recoveryCheckIn.date(),
                recoveryCheckIn.fatigueLevel(),
                recoveryCheckIn.sorenessLevel(),
                recoveryCheckIn.energyLevel(),
                recoveryCheckIn.sleepHours(),
                recoveryCheckIn.notes()
        );
    }

    public RecoveryCheckIn toDomain(RecoveryCheckInDocument document) {

        return RecoveryCheckIn.restore(
                RecoveryCheckInId.from(UUID.fromString(document.getId())),
                UserId.from(UUID.fromString(document.getUserId())),
                document.getDate(),
                document.getFatigueLevel(),
                document.getSorenessLevel(),
                document.getEnergyLevel(),
                document.getSleepHours(),
                document.getNotes()
        );
    }
}