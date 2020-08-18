package kr.co.fastcampus.Eatgo.application;

import kr.co.fastcampus.Eatgo.domain.User;
import kr.co.fastcampus.Eatgo.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void list(){
        List<User> mockUsers = new ArrayList<User>();
        mockUsers.add(User.builder()
                .name("tester")
                .email("tester@example.com")
                .level(1L)
                .build());

        given(userRepository.findAll()).willReturn(mockUsers);
        List<User> users = userService.getUsers();

        assertThat(users.get(0).getName(), is("tester"));
    }

    @Test
    public void addUser(){
        User user = User.builder()
                .name("tester")
                .email("admin@example.com")
                .level(3L)
                .build();
        given(userRepository.save(user)).willReturn(user);

        User newUser = userService.addUser(user);

        assertThat(newUser.getName(), is("tester"));
    }

    @Test
    public void update(){
        User user = User.builder()
                .id(1004L)
                .name("tester")
                .email("admin@example.com")
                .level(3L)
                .build();

        given(userRepository.findById(any())).willReturn(Optional.of(user));

        userService.updateUser(user);

        verify(userRepository).findById(any());
        verify(userRepository).save(any());
    }

    @Test
    public void deactiveUser(){
        User user = User.builder()
                .id(1004L)
                .name("tester")
                .email("admin@example.com")
                .level(1L)
                .build();

        given(userRepository.findById(any())).willReturn(Optional.of(user));

        verify(userRepository).findById(any());

    }
}