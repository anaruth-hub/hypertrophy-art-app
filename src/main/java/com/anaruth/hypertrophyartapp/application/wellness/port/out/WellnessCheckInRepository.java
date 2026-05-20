package com.anaruth.hypertrophyartapp.application.wellness.port.out;

import com.anaruth.hypertrophyartapp.domain.wellness.model.WellnessCheckIn;

public interface WellnessCheckInRepository {

    WellnessCheckIn save(WellnessCheckIn wellnessCheckIn);
}