package kr.co.fastcampus.Eatgo.application;

public class PasswordWrongException extends RuntimeException {
    public PasswordWrongException(){
        super("Wrong password");
    };
}
