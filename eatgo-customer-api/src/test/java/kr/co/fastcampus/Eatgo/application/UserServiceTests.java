package kr.co.fastcampus.Eatgo.application;

import kr.co.fastcampus.Eatgo.domain.EmailExistedException;
import kr.co.fastcampus.Eatgo.domain.User;
import kr.co.fastcampus.Eatgo.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.AssertThrows.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class UserServiceTests {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void registerUser(){
        String email = "test@example.com";
        String name = "tester";
        String password = "test";

        userService.registerUser(email, name, password);

        verify(userRepository).save(any());
    }

    @Test
    public void registerUserWithExistedEmail(){
        String email = "test@example.com";
        String name = "tester";
        String password = "test";

        User mockUser = User.builder()
                .email(email)
                .name(name)
                .build();

        given(userRepository.findByEmail(any())).willReturn(Optional.of(mockUser));

        userService.registerUser(email, name, password);
        assertThrows()
        verify(userRepository, never()).save(any());
    }
}