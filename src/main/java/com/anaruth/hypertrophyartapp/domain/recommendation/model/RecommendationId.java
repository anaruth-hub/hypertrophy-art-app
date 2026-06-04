package com.anaruth.hypertrophyartapp.domain.recommendation.model;

import java.util.UUID;

public record RecommendationId(UUID value) {

    public static RecommendationId generate() {
        return new RecommendationId(UUID.randomUUID());
    }

    public static RecommendationId from(UUID value) {
        return new RecommendationId(value);
    }
}