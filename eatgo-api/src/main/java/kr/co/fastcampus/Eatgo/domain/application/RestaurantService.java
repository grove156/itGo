package kr.co.fastcampus.Eatgo.domain.application;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import kr.co.fastcampus.Eatgo.domain.*;
import kr.co.fastcampus.Eatgo.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()->new RestaurantNotFoundException(id));

        List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(id);
        restaurant.setMenuItem(menuItems);

        return restaurant;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {

        Restaurant saved = restaurantRepository.save(restaurant);

        return saved;
    }

    @Transactional // 이 블럭을 벗어날 때 commit을해서 저장을 하게됨
    public Restaurant updateRestaurant(Long id, String name, String address) {
        //
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

        restaurant.setInformation(name, address);
        return restaurant;
    }
}
