package com.anaruth.hypertrophyartapp.infrastructure.controller.trainer;

import com.anaruth.hypertrophyartapp.application.auth.service.JwtService;
import com.anaruth.hypertrophyartapp.application.trainer.port.in.CreateTrainerResult;
import com.anaruth.hypertrophyartapp.application.trainer.port.in.CreateTrainerUseCase;
import com.anaruth.hypertrophyartapp.application.trainer.port.in.ViewMySupervisedUsersUseCase;
import com.anaruth.hypertrophyartapp.application.trainer.port.in.ViewAllTrainersUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrainerController.class)
@AutoConfigureMockMvc(addFilters = false)
class TrainerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private CreateTrainerUseCase createTrainerUseCase;

    @MockitoBean
    private ViewMySupervisedUsersUseCase viewMySupervisedUsersUseCase;

    @MockitoBean
    private ViewAllTrainersUseCase viewAllTrainersUseCase;

    @Test
    void shouldCreateTrainer() throws Exception {
        UUID trainerId = UUID.randomUUID();

        when(createTrainerUseCase.createTrainer(any()))
                .thenReturn(new CreateTrainerResult(
                        trainerId,
                        "Coach Laura",
                        "coach@test.com"
                ));

        mockMvc.perform(post("/api/trainers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Coach Laura",
                                  "email": "coach@test.com"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(trainerId.toString()))
                .andExpect(jsonPath("$.name").value("Coach Laura"))
                .andExpect(jsonPath("$.email").value("coach@test.com"));
    }
}