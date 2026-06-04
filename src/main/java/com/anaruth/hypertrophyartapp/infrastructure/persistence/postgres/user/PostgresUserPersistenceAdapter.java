package com.anaruth.hypertrophyartapp.infrastructure.persistence.postgres.user;

import com.anaruth.hypertrophyartapp.application.user.port.out.UserRepository;
import com.anaruth.hypertrophyartapp.domain.trainer.model.TrainerId;
import com.anaruth.hypertrophyartapp.domain.user.model.User;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class PostgresUserPersistenceAdapter implements UserRepository {

    private final SpringDataUserJpaRepository springDataUserJpaRepository;
    private final UserJpaMapper userJpaMapper = new UserJpaMapper();

    public PostgresUserPersistenceAdapter(SpringDataUserJpaRepository springDataUserJpaRepository) {
        this.springDataUserJpaRepository = springDataUserJpaRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = userJpaMapper.toEntity(user);
        UserEntity savedEntity = springDataUserJpaRepository.save(entity);
        return userJpaMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return springDataUserJpaRepository.findById(userId.value().toString())
                .map(userJpaMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springDataUserJpaRepository.findByEmail(email)
                .map(userJpaMapper::toDomain);

    }

    @Override
    public List<User> findByTrainerId(TrainerId trainerId) {
        return springDataUserJpaRepository.findByTrainerId(trainerId.value().toString())
                .stream()
                .map(userJpaMapper::toDomain)
                .toList();
    }
}