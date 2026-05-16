package com.anaruth.hypertrophyartapp.application.recovery.port.out;

import com.anaruth.hypertrophyartapp.domain.recovery.model.RecoveryCheckIn;

public interface RecoveryCheckInRepository {

    RecoveryCheckIn save(RecoveryCheckIn recoveryCheckIn);
}