package kr.co.fastcampus.Eatgo.application;

import kr.co.fastcampus.Eatgo.domain.User;
import kr.co.fastcampus.Eatgo.domain.UserNotFoundException;
import kr.co.fastcampus.Eatgo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User addUser(User user) {
        User newUser = userRepository.save(user);

        return newUser;
    }

    public User updateUser(User user) {
        User userBeingUpdated = userRepository.findById(user.getId()).orElseThrow(()->new UserNotFoundException(user.getId()));
        userBeingUpdated.setEmail(user.getEmail());
        userBeingUpdated.setName(user.getEmail());
        userBeingUpdated.setLevel(user.getLevel());

        User updatedUser = userRepository.save(userBeingUpdated);
        return updatedUser;
    }

    public void deactiveUser(Long id) {
        //TODO:worworwor
        User userBeingdeleted = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }
}
