package com.anaruth.hypertrophyartapp.infrastructure.persistence.mongo.recovery;

import com.anaruth.hypertrophyartapp.application.recovery.port.out.RecoveryCheckInRepository;
import com.anaruth.hypertrophyartapp.domain.recovery.model.RecoveryCheckIn;
import org.springframework.stereotype.Component;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import java.util.List;

@Component
public class MongoRecoveryCheckInPersistenceAdapter
        implements RecoveryCheckInRepository {

    private final MongoRecoveryCheckInRepository repository;

    private final RecoveryCheckInMongoMapper mapper =
            new RecoveryCheckInMongoMapper();

    public MongoRecoveryCheckInPersistenceAdapter(
            MongoRecoveryCheckInRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public RecoveryCheckIn save(RecoveryCheckIn recoveryCheckIn) {

        RecoveryCheckInDocument document =
                mapper.toDocument(recoveryCheckIn);

        RecoveryCheckInDocument saved =
                repository.save(document);

        return mapper.toDomain(saved);
    }

    @Override
    public List<RecoveryCheckIn> findByUserId(UserId userId) {
        return repository.findByUserId(userId.value().toString())
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}