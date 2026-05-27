package com.anaruth.hypertrophyartapp.application.user.service;

import com.anaruth.hypertrophyartapp.application.user.port.in.CreateUserCommand;
import com.anaruth.hypertrophyartapp.application.user.port.in.CreateUserResult;
import com.anaruth.hypertrophyartapp.application.user.port.out.UserRepository;
import com.anaruth.hypertrophyartapp.domain.user.model.User;
import com.anaruth.hypertrophyartapp.domain.user.model.UserMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserService createUserService;

    @Test
    void shouldCreateUserAndSaveIt() {
        CreateUserCommand command = new CreateUserCommand(
                "Ana",
                "ANA@TEST.COM",
                UserMode.SELF_MANAGED
        );

        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CreateUserResult result = createUserService.createUser(command);

        assertThat(result.id()).isNotNull();
        assertThat(result.name()).isEqualTo("Ana");
        assertThat(result.email()).isEqualTo("ana@test.com");
        assertThat(result.mode()).isEqualTo(UserMode.SELF_MANAGED);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();

        assertThat(savedUser.name()).isEqualTo("Ana");
        assertThat(savedUser.email()).isEqualTo("ana@test.com");
        assertThat(savedUser.mode()).isEqualTo(UserMode.SELF_MANAGED);
    }
}