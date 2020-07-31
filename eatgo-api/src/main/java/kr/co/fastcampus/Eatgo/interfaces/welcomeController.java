package kr.co.fastcampus.Eatgo.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class welcomeController {

    @GetMapping("")
    public String hello(){
        return "hello world";
    }
}
