package com.anaruth.hypertrophyartapp.infrastructure.persistence.memory;

import com.anaruth.hypertrophyartapp.application.user.port.out.UserRepository;
import com.anaruth.hypertrophyartapp.domain.user.model.User;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Map<UUID, User> users = new ConcurrentHashMap<>();

    @Override
    public User save(User user) {
        users.put(user.id().value(), user);
        return user;
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return Optional.ofNullable(users.get(userId.value()));

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.values()
                .stream()
                .filter(user -> user.email().equalsIgnoreCase(email))
                .findFirst();
    }
}