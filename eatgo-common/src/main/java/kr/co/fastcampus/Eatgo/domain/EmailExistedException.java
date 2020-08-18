package kr.co.fastcampus.Eatgo.domain;

public class EmailExistedException extends RuntimeException{
    public EmailExistedException(String email){
        super("Email has been registered already: "+email);
    }
}
