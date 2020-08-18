package kr.co.fastcampus.Eatgo.application;

import kr.co.fastcampus.Eatgo.domain.EmailExistedException;
import kr.co.fastcampus.Eatgo.domain.User;
import kr.co.fastcampus.Eatgo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String email, String name, String password) {

        Optional<User> existed = userRepository.findByEmail(email);

        if(existed.isPresent()){
            throw new EmailExistedException(email);
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();//BCript로 비밀번호 해싱(암호화) -선언
        String encodedPassword = passwordEncoder.encode(password); //실제 인코딩 구형

        User user = User.builder()
                .name(name)
                .email(email)
                .password(encodedPassword)
                .level(1L)
                .build();

        User newUser = userRepository.save(user);
        return newUser;
    }
}
