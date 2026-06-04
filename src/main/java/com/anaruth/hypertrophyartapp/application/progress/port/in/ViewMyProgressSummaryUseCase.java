package com.anaruth.hypertrophyartapp.application.progress.port.in;

import java.util.UUID;

public interface ViewMyProgressSummaryUseCase {

    ProgressSummaryResult viewMyProgressSummary(UUID authenticatedUserId);
}