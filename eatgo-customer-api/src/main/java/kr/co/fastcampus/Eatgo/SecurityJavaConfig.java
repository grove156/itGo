package kr.co.fastcampus.Eatgo;

import kr.co.fastcampus.Eatgo.filters.JwtAuthenticationFilter;
import kr.co.fastcampus.Eatgo.utils.JwtUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.Filter;

@Configuration//설정 자바파일에는 이 어노테이션을 붙어야함
@EnableWebSecurity//웹 시큐리티를 사용 가능하게 설정합
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter{//자바기반 설정으로 Spring Security를 사용할 수 있다 WebSecurityConfigurerAdapter를상속받아 빠르게 설정이 가능하다

    @Value("${jwt.secret}")
    private String secret;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       Filter filter = new JwtAuthenticationFilter(authenticationManager(),jwtUtil());//BasicAuthenticationFilter을 상속받음

        http.formLogin().disable()//디폴트 로그인 폼을 없앰
            .csrf().disable() //csrf인증 기능을 끔
            .cors().disable() //cors기능을 끔
                .headers().frameOptions().disable()//iframe 차단기능을 끔
            .and() //and는 .header에서 http로 나오기 위해 사용
                .addFilter(filter)//필터만들어서 적용
            .sessionManagement()//세션관리 설정
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//서버에 값을 세션값을 저장하지 않고 stateless로 설정함
    }

    @Bean//BCryptPasswordEncoder빈 등록
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean//JwtUtil 빈 등록
    public JwtUtil jwtUtil(){
        return new JwtUtil(secret);
    }
}
