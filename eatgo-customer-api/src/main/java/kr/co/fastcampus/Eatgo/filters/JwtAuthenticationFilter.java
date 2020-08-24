package kr.co.fastcampus.Eatgo.filters;

import io.jsonwebtoken.Claims;
import kr.co.fastcampus.Eatgo.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {//JwtUtil을 사용하기 위해서 생성자로 받음
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }

    @Override//doFilterInternal은 BasicAuthenticationFilter를 override함
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        Authentication authentication = getAuthentication(request);
        if(authentication != null){
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
        }
        chain.doFilter(request, response);// 체인을 통해 다음작업으로 계속 연결됨
    }

    private Authentication getAuthentication(HttpServletRequest request){
        String token = request.getHeader("Authorization");//header안에 있는 Authentication 값은 없을수도 있기 때문에 예외처리가 필요함

        if(token == null){
            return null;
        }
        //header는 Authorization :Bearer fsdgssdgsdgsd32f3.3f233r32r53....
        //와 같이 되어 있기 때문에 Bearerㄹ르 서브스트링으로 제거해주고 넘겨야한다
        Claims claims = jwtUtil.getClamins(token.substring("Bearer ".length()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(claims, null);

        return authentication;
    }
}
