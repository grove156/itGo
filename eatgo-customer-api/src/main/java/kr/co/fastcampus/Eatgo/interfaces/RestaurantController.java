package kr.co.fastcampus.Eatgo.interfaces;

import kr.co.fastcampus.Eatgo.application.RestaurantService;
import kr.co.fastcampus.Eatgo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin//SPA 서버와 연동하기 위해서 사용되는 태그
@RestController
public class RestaurantController {

    @Autowired
    public RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        return restaurants;
    }

    @GetMapping("/restaurant/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {

        Restaurant restaurant = restaurantService.getRestaurant(id);

       // List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(id);
       // restaurant.setMenuItem(menuItems);

        return restaurant;
    }
}
