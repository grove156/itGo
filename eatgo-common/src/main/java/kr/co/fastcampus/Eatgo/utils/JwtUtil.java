package kr.co.fastcampus.Eatgo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

public class JwtUtil {

    private Key key;

    public JwtUtil(String secret){//외부에서(키값음 property.yml에 넣어놈) 시크릿 키 주입
        this.key = Keys.hmacShaKeyFor(secret.getBytes());

    }

    public String createToken(long id, String name, Long restaurantId) {//JWT생성
        String token = Jwts.builder()
                .claim("userId",id)
                .claim("name",name)
                .claim("restaurantId",restaurantId)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    public Claims getClamins(String token) {//JWT조회(?)
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)//싸인이 포함된 jwt = jws
                .getBody();
        return claims;
    }
}
