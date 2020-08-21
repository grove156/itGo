package kr.co.fastcampus.Eatgo.application;

import kr.co.fastcampus.Eatgo.domain.EmailExistedException;
import kr.co.fastcampus.Eatgo.domain.User;
import kr.co.fastcampus.Eatgo.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
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

    @Mock
    private PasswordEncoder passwordEncoder;

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
        verify(userRepository, never()).save(any());
    }

    @Test
    public void authenicationWithValidEmail(){
        String email = "tester@example.com";
        String password = "test";

        User mockUser = User.builder().email(email).password(password).build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));
        given(passwordEncoder.matches(any(),any())).willReturn(true);
        User user = userService.authenticate(email, password);

        assertThat(user.getEmail(), is(email));
    }

    @Test
    public void authenicationWithInvalidEmail(){
        String email = "x@example.com";
        String password = "test";

        User mockUser = User.builder().email(email).password(password).build();

        given(userRepository.findByEmail(email)).willReturn(Optional.empty());
        given(passwordEncoder.matches(any(),any())).willReturn(false);


        User user = userService.authenticate(email, password);

        assertThat(user.getEmail(), is(email));
    }
}