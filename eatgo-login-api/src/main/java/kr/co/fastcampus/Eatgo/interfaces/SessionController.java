package kr.co.fastcampus.Eatgo.interfaces;


import kr.co.fastcampus.Eatgo.application.UserService;
import kr.co.fastcampus.Eatgo.domain.User;
import kr.co.fastcampus.Eatgo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/session")
        public ResponseEntity<?> create(@RequestBody SessionRequestDto resource) throws URISyntaxException {
            String url = "/session";
            String email = resource.getEmail();
            String password = resource.getPassword();

            User user = userService.authenticate(email,password);


            String accessToken = jwtUtil.createToken(user.getId(),user.getName(),user.getRestaurantId());


            return ResponseEntity.created(new URI(url)).header("authrization",accessToken).body(user);
        }

}

