package kr.co.fastcampus.Eatgo.application;

import kr.co.fastcampus.Eatgo.domain.*;

import kr.co.fastcampus.Eatgo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new EmailNotExistedException(email));

        //PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean passwordMatch = passwordEncoder.matches(password, user.getPassword());//받은 패스워드와 디비에 저장된 해시값을 비교

        if(!passwordMatch){
            throw new PasswordWrongException();
        }


        return user;
    }
}
