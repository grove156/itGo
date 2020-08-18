package kr.co.fastcampus.Eatgo.interfaces;

import kr.co.fastcampus.Eatgo.application.RestaurantService;
import kr.co.fastcampus.Eatgo.domain.MenuItem;
import kr.co.fastcampus.Eatgo.domain.Restaurant;
import kr.co.fastcampus.Eatgo.domain.RestaurantNotFoundException;
import kr.co.fastcampus.Eatgo.domain.Review;
import kr.co.fastcampus.Eatgo.interfaces.RestaurantController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class) //스프링을 이용해서 테스트를 진행함 @Test는 자바에서도 있슴 @SpringBootTest의 하위호환
@WebMvcTest(RestaurantController.class)//web mvc를 테스트 하기위해서 사용함 get,post등의 리퀘스트를 보내기 위해서
class RestaurantControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean//mokito를 이용간 가짜 객체 단위테스트에서 Service에 의존하지 않고 service가 우리가 정해진 값을 보내주고 여기서는 controller만 테스트
    private RestaurantService restaurantService;

    //테스트를 위해 객체를 Restaurant을 직접 주입햇던 경우
    //@SpyBean(RestaurantRepositoryImpl.class) //컨트롤러에 원하는 객체를 주입 할 수 있다.
    //private RestaurantRepositoryImpl restaurantRepositoryImpl;

    //@SpyBean(MenuItemRepositoryImpl.class)
    //private MenuItemRepositoryImpl menuItemRepository;

    //@SpyBean(RestaurantService.class)
    //private RestaurantService restaurantService;


    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        restaurants.add(
                Restaurant.builder()
                        .id(1004L)
                        .categoryId(1L)
                        .name("Bob zip")
                        .address("Seoul")
                        .build()
        );
        //mokito를 이용한 가짜객체 주입
        given(restaurantService.getRestaurants("seoul",1L)).willReturn(restaurants);

        //mockMvc를 이용해서 테스트
        mvc.perform(get("/restaurants?region=seoul&category=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1004")))
                .andExpect(content().string(containsString("\"name\":\"Bob zip\"")));
    }

    @Test
    public void detailWithExisted() throws Exception {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        MenuItem menuItem = MenuItem.builder()
                .name("Kimchi")
                .build();
        restaurant.setMenuItem(Arrays.asList(menuItem));

        Review review = Review.builder()
                .name("Joker")
                .score(5)
                .description("good")
                .build();

        restaurant.setReviews(Arrays.asList(review));

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);

        mvc.perform(get("/restaurant/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1004")))
                .andExpect(content().string(containsString("\"name\":\"Bob zip\"")))
                .andExpect(content().string(containsString("Kimchi")))
                .andExpect(content().string(containsString("good")));
    }

    @Test
    public void detailWithNotExisted() throws Exception {
        given(restaurantService.getRestaurant(404L)).willThrow(new RestaurantNotFoundException(404L));

        mvc.perform(get("/restaurant/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }

}