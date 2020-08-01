package kr.co.fastcampus.Eatgo.domain;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Component //빈등록 좀더 세분화 하자만 @Service, @Repository등이 있음
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private List<Restaurant> restaurants = new ArrayList<Restaurant>();

    public RestaurantRepositoryImpl(){
        restaurants.add(new Restaurant(2020L,"Cyber food", "Seoul"));
        restaurants.add(new Restaurant(1004L,"Bob zip", "Seoul"));
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurants;
    }

    @Override
    public Restaurant findById(Long id) {

        Restaurant restaurant = restaurants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);

        return restaurant;
    }
}
