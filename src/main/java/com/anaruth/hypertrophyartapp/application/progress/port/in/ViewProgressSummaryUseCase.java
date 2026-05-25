package com.anaruth.hypertrophyartapp.application.progress.port.in;

import java.util.UUID;

public interface ViewProgressSummaryUseCase {

    ProgressSummaryResult viewByUserId(UUID userId);
}