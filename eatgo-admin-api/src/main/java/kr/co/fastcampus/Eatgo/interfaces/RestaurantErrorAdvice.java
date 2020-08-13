package kr.co.fastcampus.Eatgo.interfaces;

import kr.co.fastcampus.Eatgo.domain.RestaurantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice//예외처리해주는 빈
public class RestaurantErrorAdvice {

    @ResponseBody //리스폰스 바디라는걸 선언하고 리턴 내용을 http리퀘스트 바디에 심어서 보내줌
    @ResponseStatus(HttpStatus.NOT_FOUND)//http스테이터스에 낫파운드를 날림
    @ExceptionHandler(RestaurantNotFoundException.class)
    public String handleNotFound(){
        return "{}";
    }
}
