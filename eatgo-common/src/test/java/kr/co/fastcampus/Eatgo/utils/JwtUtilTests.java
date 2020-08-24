package kr.co.fastcampus.Eatgo.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class JwtUtilTests {

    private static final String SECRET= "goodlekkfekfosd@424fe4oj5ojofjsdifjhij3lkfjl3jlkrjldk1252f";
    private JwtUtil jwtUtil;


    @BeforeEach
    public void setUp(){
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    public void createToken(){
        JwtUtil jwtUtil = new JwtUtil(SECRET);

        String token = jwtUtil.createToken(1004L, "John");

        assertThat(token, containsString("."));
    }

    @Test
    public void getClaims(){
        String token ="eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb2huIn0.aL5SFNFszqxyMILjvqYeXDCVgLa_wEmTt3GdaRGQPkc";
        Claims claims = jwtUtil.getClamins(token);

        assertThat(claims.get("name"), is("John"));
        assertThat(claims.get("userId",Long.class), is(1004L));
    }
}