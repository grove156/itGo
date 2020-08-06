package kr.co.fastcampus.Eatgo.interfaces;

import kr.co.fastcampus.Eatgo.domain.MenuItem;
import kr.co.fastcampus.Eatgo.domain.MenuItemRepository;
import kr.co.fastcampus.Eatgo.domain.Restaurant;
import kr.co.fastcampus.Eatgo.domain.RestaurantRepository;
import kr.co.fastcampus.Eatgo.domain.application.RestaurantService;
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

    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant resource) throws URISyntaxException { //ResponseEntity는 http response를 핸들리 항기 위해 사용한다,
        String name = resource.getName();
        String address = resource.getAddress();
        Restaurant restaurant = new Restaurant(name, address);
        Restaurant created = restaurantService.addRestaurant(restaurant);

        URI location = new URI("/restaurants/1234");
        return ResponseEntity.created(location).body("{}"); //response 코드를 201로 주기 위해서 created(location)을 사용함 .body는 바디안에 들어갈 내용
    }

    @PatchMapping("/restaurants/{id}")
    public String update(@PathVariable Long id,@Valid @RequestBody Restaurant restaurant){
        String name = restaurant.getName();
        String address = restaurant.getAddress();
        restaurantService.updateRestaurant(id, name, address);

        return "{}";
    }
}
