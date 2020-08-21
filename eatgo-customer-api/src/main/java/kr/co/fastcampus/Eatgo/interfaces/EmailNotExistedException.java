package kr.co.fastcampus.Eatgo.interfaces;

import lombok.Getter;
import lombok.Setter;

public class EmailNotExistedException extends RuntimeException {

    @Setter
    public static String errorMessage;
    public EmailNotExistedException(String email){
        super("email is not existed: "+ email);
        errorMessage = "{\"errorMessage\": \"emails is not existed: \" "+ email +"\"}";
    }
}
