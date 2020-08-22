package kr.co.fastcampus.Eatgo.utils;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTests {
    @Test
    public void createToken(){
        String secret= "goodlekkfekfosd@424fe4oj5ojofjsdifjhij3lkfjl3jlkrjldk1252f";
        JwtUtil jwtUtil = new JwtUtil(secret);

        String token = jwtUtil.createToken(1004L, "John");

        assertThat(token, containsString("."));
    }
}