package kr.co.fastcampus.Eatgo.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserTests {
    @Test
    public void creation(){
        User user = User.builder()
                .email("example@gmail.com")
                .name("tester")
                .level(3L)
                .build();
        assertThat(user.getName(), is("tester"));
        assertThat(user.isAdmin(), is(true));
    }

    @Test
    public void isRestaurantOwner(){
        User user = User.builder()
                .email("example@gmail.com")
                .name("tester")
                .level(1L)
                .build();

        assertThat(user.isRestaurantOwner(), is(true));
        assertThat(user.getRestaurantId(), is(1004L));
    }
}