package kr.co.fastcampus.Eatgo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.security.util.Password;

@Configuration//설정 자바파일에는 이 어노테이션을 붙어야함
@EnableWebSecurity//웹 시큐리티를 사용 가능하게 설정합
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter{//자바기반 설정으로 Spring Security를 사용할 수 있다 WebSecurityConfigurerAdapter를상속받아 빠르게 설정이 가능하다

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable()//디폴트 로그인 폼을 없앰
            .csrf().disable() //csrf인증 기능을 끔
            .cors().disable() //cors기능을 끔
            .headers().frameOptions().disable();//iframe 차단기능을 끔
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
