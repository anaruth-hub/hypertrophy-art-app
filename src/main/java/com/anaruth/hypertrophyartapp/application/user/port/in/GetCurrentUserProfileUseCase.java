package com.anaruth.hypertrophyartapp.application.user.port.in;

import java.util.UUID;

public interface GetCurrentUserProfileUseCase {

    CurrentUserProfileResult getCurrentUserProfile(UUID authenticatedUserId);
}