package com.anaruth.hypertrophyartapp.application.progress.port.in;

import java.util.UUID;

public interface ViewAssignedUserProgressUseCase {

    ProgressSummaryResult viewAssignedUserProgress(
            UUID trainerId, UUID userId
    );
}