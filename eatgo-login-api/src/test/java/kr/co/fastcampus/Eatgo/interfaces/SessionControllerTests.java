package kr.co.fastcampus.Eatgo.interfaces;

import kr.co.fastcampus.Eatgo.application.UserService;
import kr.co.fastcampus.Eatgo.domain.EmailNotExistedException;
import kr.co.fastcampus.Eatgo.domain.PasswordWrongException;
import kr.co.fastcampus.Eatgo.domain.User;
import kr.co.fastcampus.Eatgo.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;


import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(SessionController.class)
class SessionControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserService userService;



    @Test
    public void createWithValidAttribute() throws Exception {

        String email = "tester@example.com";
        String password = "test";
        Long id = 1004L;
        String name = "Kim";

        String token = "header.payload.signiture";

        User mockUser = User.builder().name(name).id(id).build();

        given(userService.authenticate(email,password)).willReturn(mockUser);
        given(jwtUtil.createToken(id,name)).willReturn("header.payload.signiture");

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"tester@example.com\", \"password\": \"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/session"))
                .andExpect(content().string(containsString("{\"accessToken\":}")))
                .andExpect(content().string(containsString(".")));

        verify(userService).authenticate(eq(email),eq(password));
    }

    @Test
    public void createWithInvalidPassword() throws Exception {
        given(userService.authenticate("tester@example.com","xxx")).willThrow(PasswordWrongException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"tester@example.com\", \"password\": \"xxx\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("tester@example.com"),eq("xxx"));
    }

    @Test
    public void createWithInvalidEmail() throws Exception {
        given(userService.authenticate("tester@example.com","xxx")).willThrow(
                EmailNotExistedException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"aa@example.com\", \"password\": \"test\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("aa@example.com"),eq("test"));
    }
}