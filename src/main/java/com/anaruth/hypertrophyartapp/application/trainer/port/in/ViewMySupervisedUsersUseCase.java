package com.anaruth.hypertrophyartapp.application.trainer.port.in;

import java.util.List;
import java.util.UUID;

public interface ViewMySupervisedUsersUseCase {

    List<SupervisedUserResult> viewMySupervisedUsers(UUID authenticatedTrainerId);
}