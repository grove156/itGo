package kr.co.fastcampus.Eatgo.interfaces;

import kr.co.fastcampus.Eatgo.application.UserService;
import kr.co.fastcampus.Eatgo.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;


    @Test
    public void list() throws Exception {
        List<User> users = new ArrayList<User>();
        users.add(User.builder()
                .name("tester")
                .email("tester@example.com")
                .level(1L)
                .build());

        given(userService.getUsers()).willReturn(users);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("tester")));
    }

    @Test
    public void create() throws Exception {
        User user = User.builder()
                .email("admin@example.com")
                .name("admin1")
                .level(3L)
                .build();

        given(userService.addUser(any())).willReturn(user);

        mvc.perform(post("/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"admin@example.com\",\"name\":\"Admin1\"}"))
        .andExpect(status().isCreated());

        verify(userService).addUser(any());
    }

    @Test
    public void update() throws Exception {
        User user = User.builder()
                .id(1004L)
                .email("admin@example.com")
                .name("admin1")
                .level(3L)
                .build();

        mvc.perform(patch("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1004,\"level\":3,\"email\":\"admin@example.com\",\"name\":\"Admin1\"}"))
                .andExpect(status().isOk());

        verify(userService).updateUser(any());
    }

    @Test
    public void deactivate() throws Exception {
        mvc.perform(delete("/users/1004"))
                .andExpect(status().isOk());

        verify(userService).deactiveUser(any());
    }
}