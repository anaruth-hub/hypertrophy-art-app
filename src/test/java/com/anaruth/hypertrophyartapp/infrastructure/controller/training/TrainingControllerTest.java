package com.anaruth.hypertrophyartapp.infrastructure.controller.training;

import com.anaruth.hypertrophyartapp.application.auth.service.JwtService;
import com.anaruth.hypertrophyartapp.application.training.port.in.RegisterTrainingResult;
import com.anaruth.hypertrophyartapp.application.training.port.in.RegisterTrainingUseCase;
import com.anaruth.hypertrophyartapp.domain.auth.model.Role;
import com.anaruth.hypertrophyartapp.domain.training.model.TrainingIntensity;
import com.anaruth.hypertrophyartapp.infrastructure.security.AuthenticatedAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrainingController.class)
@AutoConfigureMockMvc(addFilters = false)
class TrainingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private RegisterTrainingUseCase registerTrainingUseCase;

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void shouldRegisterTraining() throws Exception {
        UUID trainingId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        when(registerTrainingUseCase.registerTraining(any()))
                .thenReturn(new RegisterTrainingResult(
                        trainingId,
                        userId,
                        LocalDate.of(2026, 5, 24),
                        "legs",
                        "squat, leg press",
                        TrainingIntensity.HIGH,
                        75
                ));

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        new AuthenticatedAccount(userId, "ana@test.com", Role.USER),
                        null
                )
        );

        mockMvc.perform(post("/api/trainings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "date": "2026-05-24",
                                  "muscleGroup": "legs",
                                  "exercises": "squat, leg press",
                                  "intensity": "HIGH",
                                  "durationMinutes": 75
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(trainingId.toString()))
                .andExpect(jsonPath("$.userId").value(userId.toString()))
                .andExpect(jsonPath("$.muscleGroup").value("legs"))
                .andExpect(jsonPath("$.exercises").value("squat, leg press"))
                .andExpect(jsonPath("$.intensity").value("HIGH"))
                .andExpect(jsonPath("$.durationMinutes").value(75));
    }
}
