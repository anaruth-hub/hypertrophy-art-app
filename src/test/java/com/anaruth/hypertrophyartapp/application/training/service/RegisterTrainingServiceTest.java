package com.anaruth.hypertrophyartapp.application.training.service;

import com.anaruth.hypertrophyartapp.application.training.port.in.RegisterTrainingCommand;
import com.anaruth.hypertrophyartapp.application.training.port.in.RegisterTrainingResult;
import com.anaruth.hypertrophyartapp.application.training.port.out.TrainingRepository;
import com.anaruth.hypertrophyartapp.application.user.port.out.UserRepository;
import com.anaruth.hypertrophyartapp.domain.training.model.Training;
import com.anaruth.hypertrophyartapp.domain.training.model.TrainingIntensity;
import com.anaruth.hypertrophyartapp.domain.user.model.User;
import com.anaruth.hypertrophyartapp.domain.user.model.UserId;
import com.anaruth.hypertrophyartapp.domain.user.model.UserMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterTrainingServiceTest {

    @Mock
    private TrainingRepository trainingRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RegisterTrainingService registerTrainingService;

    @Test
    void shouldRegisterTrainingWhenUserExists() {
        UUID userUuid = UUID.randomUUID();
        UserId userId = UserId.from(userUuid);

        User user = User.restore(
                userId,
                "Ana",
                "ana@test.com",
                UserMode.SELF_MANAGED,
                null
        );

        RegisterTrainingCommand command = new RegisterTrainingCommand(
                userUuid,
                LocalDate.of(2026, 5, 24),
                "legs",
                "squat, leg press",
                TrainingIntensity.HIGH,
                75
        );

        when(userRepository.findById(any(UserId.class)))
                .thenReturn(Optional.of(user));

        when(trainingRepository.save(any(Training.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        RegisterTrainingResult result =
                registerTrainingService.registerTraining(command);

        assertThat(result.id()).isNotNull();
        assertThat(result.userId()).isEqualTo(userUuid);
        assertThat(result.date()).isEqualTo(LocalDate.of(2026, 5, 24));
        assertThat(result.muscleGroup()).isEqualTo("legs");
        assertThat(result.exercises()).isEqualTo("squat, leg press");
        assertThat(result.intensity()).isEqualTo(TrainingIntensity.HIGH);
        assertThat(result.durationMinutes()).isEqualTo(75);

        verify(userRepository).findById(any(UserId.class));

        ArgumentCaptor<Training> trainingCaptor =
                ArgumentCaptor.forClass(Training.class);

        verify(trainingRepository).save(trainingCaptor.capture());

        Training savedTraining = trainingCaptor.getValue();

        assertThat(savedTraining.userId().value()).isEqualTo(userUuid);
        assertThat(savedTraining.muscleGroup()).isEqualTo("legs");
        assertThat(savedTraining.intensity()).isEqualTo(TrainingIntensity.HIGH);
    }
}