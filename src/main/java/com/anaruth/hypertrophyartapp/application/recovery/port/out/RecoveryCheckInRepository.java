package com.anaruth.hypertrophyartapp.application.recovery.port.out;

import com.anaruth.hypertrophyartapp.domain.recovery.model.RecoveryCheckIn;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import java.util.List;

public interface RecoveryCheckInRepository {
    List<RecoveryCheckIn> findByUserId(UserId userId);
    RecoveryCheckIn save(RecoveryCheckIn recoveryCheckIn);
}