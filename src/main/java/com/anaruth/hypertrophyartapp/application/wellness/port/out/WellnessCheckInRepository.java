package com.anaruth.hypertrophyartapp.application.wellness.port.out;

import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import com.anaruth.hypertrophyartapp.domain.wellness.model.WellnessCheckIn;
import java.util.List;

public interface WellnessCheckInRepository {
    List<WellnessCheckIn> findByUserId(UserId userId);
    WellnessCheckIn save(WellnessCheckIn wellnessCheckIn);
}