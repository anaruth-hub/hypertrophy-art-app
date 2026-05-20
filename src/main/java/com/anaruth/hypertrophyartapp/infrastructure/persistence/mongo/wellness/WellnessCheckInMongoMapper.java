package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.wellness;

import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import com.anaruth.hypertrophyartapp.domain.wellness.model.WellnessCheckIn;
import com.anaruth.hypertrophyartapp.domain.wellness.model.WellnessCheckInId;

import java.util.UUID;

public class WellnessCheckInMongoMapper {

    public WellnessCheckInDocument toDocument(WellnessCheckIn wellnessCheckIn) {

        return new WellnessCheckInDocument(
                wellnessCheckIn.id().value().toString(),
                wellnessCheckIn.userId().value().toString(),
                wellnessCheckIn.date(),
                wellnessCheckIn.physicalState(),
                wellnessCheckIn.mentalState(),
                wellnessCheckIn.emotionalState(),
                wellnessCheckIn.stressLevel(),
                wellnessCheckIn.motivationLevel(),
                wellnessCheckIn.notes()
        );
    }

    public WellnessCheckIn toDomain(WellnessCheckInDocument document) {

        return WellnessCheckIn.restore(
                WellnessCheckInId.from(UUID.fromString(document.getId())),
                UserId.from(UUID.fromString(document.getUserId())),
                document.getDate(),
                document.getPhysicalState(),
                document.getMentalState(),
                document.getEmotionalState(),
                document.getStressLevel(),
                document.getMotivationLevel(),
                document.getNotes()
        );
    }
}