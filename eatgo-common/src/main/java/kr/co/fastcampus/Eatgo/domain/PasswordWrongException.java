package kr.co.fastcampus.Eatgo.domain;

public class PasswordWrongException extends RuntimeException {
    public PasswordWrongException(){
        super("Wrong password");
    };
}
