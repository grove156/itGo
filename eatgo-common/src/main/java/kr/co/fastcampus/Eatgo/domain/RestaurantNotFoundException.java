package kr.co.fastcampus.Eatgo.domain;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(Long id){
        super("Could not find the restaurant " + id);
    }
}
