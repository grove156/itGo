package kr.co.fastcampus.Eatgo.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantRepository {

    private List<Restaurant> restaurants = new ArrayList<Restaurant>();

    public RestaurantRepository(){
        restaurants.add(new Restaurant(2020L,"Cyber food", "Seoul"));
        restaurants.add(new Restaurant(1004L,"Bob zip", "Seoul"));
    }

    public List<Restaurant> findAll() {

        return restaurants;
    }

    public Restaurant findById(Long id) {

        Restaurant restaurant = restaurants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);

        return restaurant;
    }
}
