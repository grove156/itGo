package kr.co.fastcampus.Eatgo.domain.application;

import kr.co.fastcampus.Eatgo.domain.MenuItem;
import kr.co.fastcampus.Eatgo.domain.MenuItemRepository;
import kr.co.fastcampus.Eatgo.domain.Restaurant;
import kr.co.fastcampus.Eatgo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository){
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }

    public Restaurant getRestaurant(Long id){
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

        List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(id);
        restaurant.setMenuItem(menuItems);

        return restaurant;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {

        Restaurant saved = restaurantRepository.save(restaurant);

        return saved;
    }
}
