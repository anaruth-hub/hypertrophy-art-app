package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.wellness;

import com.anaruth.hypertrophyartapp.application.wellness.port.out.WellnessCheckInRepository;
import com.anaruth.hypertrophyartapp.domain.wellness.model.WellnessCheckIn;
import org.springframework.stereotype.Component;

@Component
public class MongoWellnessCheckInPersistenceAdapter
        implements WellnessCheckInRepository {

    private final MongoWellnessCheckInRepository repository;

    private final WellnessCheckInMongoMapper mapper =
            new WellnessCheckInMongoMapper();

    public MongoWellnessCheckInPersistenceAdapter(
            MongoWellnessCheckInRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public WellnessCheckIn save(WellnessCheckIn wellnessCheckIn) {

        WellnessCheckInDocument document =
                mapper.toDocument(wellnessCheckIn);

        WellnessCheckInDocument saved =
                repository.save(document);

        return mapper.toDomain(saved);
    }
}