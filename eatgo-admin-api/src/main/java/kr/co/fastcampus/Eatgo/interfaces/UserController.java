package kr.co.fastcampus.Eatgo.interfaces;

import kr.co.fastcampus.Eatgo.application.UserService;
import kr.co.fastcampus.Eatgo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> list(){
        List<User> users = userService.getUsers();
        return users;
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException {
        User user = userService.addUser(resource);

        String url = "/users/"+user.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

    @PatchMapping("/users")
    public User update(@RequestBody User resource){
        return userService.updateUser(resource);
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.deactiveUser(id);
        return null;
    }
}
