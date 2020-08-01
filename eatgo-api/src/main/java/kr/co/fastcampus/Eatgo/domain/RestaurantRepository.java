package kr.co.fastcampus.Eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> findAll();

    Restaurant findById(Long id);
}
