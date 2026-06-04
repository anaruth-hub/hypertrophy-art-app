package com.anaruth.hypertrophyartapp.application.progress.service;

import com.anaruth.hypertrophyartapp.application.progress.port.in.ProgressSummaryResult;
import com.anaruth.hypertrophyartapp.application.progress.port.in.ViewMyProgressSummaryUseCase;
import com.anaruth.hypertrophyartapp.application.progress.port.in.ViewProgressSummaryUseCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ViewMyProgressSummaryService implements ViewMyProgressSummaryUseCase {

    private final ViewProgressSummaryUseCase viewProgressSummaryUseCase;

    public ViewMyProgressSummaryService(
            ViewProgressSummaryUseCase viewProgressSummaryUseCase
    ) {
        this.viewProgressSummaryUseCase = viewProgressSummaryUseCase;
    }

    @Override
    public ProgressSummaryResult viewMyProgressSummary(UUID authenticatedUserId) {
        return viewProgressSummaryUseCase.viewByUserId(authenticatedUserId);
    }
}