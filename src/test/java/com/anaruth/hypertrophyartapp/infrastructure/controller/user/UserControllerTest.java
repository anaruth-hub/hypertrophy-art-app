package com.anaruth.hypertrophyartapp.infrastructure.controller.user;

import com.anaruth.hypertrophyartapp.application.auth.service.JwtService;
import com.anaruth.hypertrophyartapp.application.user.port.in.CreateUserResult;
import com.anaruth.hypertrophyartapp.application.user.port.in.CreateUserUseCase;
import com.anaruth.hypertrophyartapp.application.user.port.in.AssignTrainerToUserResult;
import com.anaruth.hypertrophyartapp.application.user.port.in.AssignTrainerToUserUseCase;
import com.anaruth.hypertrophyartapp.domain.auth.model.Role;
import com.anaruth.hypertrophyartapp.domain.user.model.UserMode;
import com.anaruth.hypertrophyartapp.infrastructure.security.AuthenticatedAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import com.anaruth.hypertrophyartapp.application.user.port.in.GetCurrentUserProfileUseCase;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private CreateUserUseCase createUserUseCase;

    @MockitoBean
    private AssignTrainerToUserUseCase assignTrainerToUserUseCase;

    @MockitoBean
    private GetCurrentUserProfileUseCase getCurrentUserProfileUseCase;

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void shouldCreateUser() throws Exception {
        UUID userId = UUID.randomUUID();

        when(createUserUseCase.createUser(any()))
                .thenReturn(new CreateUserResult(
                        userId,
                        "Ana",
                        "ana@test.com",
                        UserMode.SELF_MANAGED
                ));

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Ana",
                                  "email": "ana@test.com",
                                  "mode": "SELF_MANAGED"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(userId.toString()))
                .andExpect(jsonPath("$.name").value("Ana"))
                .andExpect(jsonPath("$.email").value("ana@test.com"))
                .andExpect(jsonPath("$.mode").value("SELF_MANAGED"));
    }

    @Test
    void shouldAssignTrainerToCurrentUser() throws Exception {
        UUID userId = UUID.randomUUID();
        UUID trainerId = UUID.randomUUID();

        when(assignTrainerToUserUseCase.assignTrainer(any()))
                .thenReturn(new AssignTrainerToUserResult(
                        userId,
                        "Ana",
                        UserMode.SUPERVISED,
                        trainerId
                ));

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        new AuthenticatedAccount(userId, "ana@test.com", Role.USER),
                        null
                )
        );

        mockMvc.perform(post("/api/users/me/assign-trainer/{trainerId}", trainerId)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId.toString()))
                .andExpect(jsonPath("$.userName").value("Ana"))
                .andExpect(jsonPath("$.mode").value("SUPERVISED"))
                .andExpect(jsonPath("$.trainerId").value(trainerId.toString()));
    }
}
