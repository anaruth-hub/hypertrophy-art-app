package com.anaruth.hypertrophyartapp.application.user.port.out;

import com.anaruth.hypertrophyartapp.domain.user.model.User;

public interface UserRepository {

    User save(User user);
}