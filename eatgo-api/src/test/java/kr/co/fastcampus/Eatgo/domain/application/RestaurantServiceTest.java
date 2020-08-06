package kr.co.fastcampus.Eatgo.domain.application;

import kr.co.fastcampus.Eatgo.domain.*;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock // mokito의 @Mock으로 설정하므로서 RestaurantRepository에서 직접 주입받지 않고 가짜 객체를 만들어서 진짜 저장된 것처럼 행동함. 단위테스트를 하기위해서 이렇게 함
    private RestaurantRepository restaurantRepository;

    @Mock // mokito의 @Mock으로 설정하므로서 RestaurantRepository에서 직접 주입받지 않고 가짜 객체를 만들어서 진짜 저장된 것처럼 행동함. 단위테
    private MenuItemRepository menuItemRepository;

    @BeforeEach //AOP? 테스트 할때 테스트 하기전에 항상 먼저 실행됨
    public void setUp(){
        MockitoAnnotations.initMocks(this); //목 객체 할당 @Mock 이 붙은 객체를 초기화 시켜줌

        mockRestaurantRepository(); //가짜 객체 주입, 아래에 있음
        mockMenuItemRepository();

        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
    }

    @Test
    public void getRestaurantWithExist(){
        Restaurant restaurant = restaurantService.getRestaurant(1004L);
        assertThat(restaurant.getId(), is(1004L));

        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertThat(menuItem.getName(), is("Kimchi"));
    }

    @Test
    public void getRestaurantWithoutExist(){
        try {
            Restaurant restaurant = restaurantService.getRestaurant(404L);
        }catch(RestaurantNotFoundException e){
            Assertions.assertEquals("Could not find the restaurant 404",e.getMessage());
        }

    }

    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        assertThat(restaurants.get(0).getId(), is(1004L));
    }

    @Test
    public void addRestaurant(){
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });

        Restaurant restaurant = Restaurant.builder()
                .name("BeRyong")
                .address("Seoul")
                .build();
        Restaurant created = restaurantService.addRestaurant(restaurant);
        assertThat(created.getId(), is(1234L));
    }

    @Test
    public void updateRestaurant(){
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();


        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

        restaurantService.updateRestaurant(1004L,"JokerHouse","Busan");

        assertThat(restaurant.getName(), is("JokerHouse"));
        assertThat(restaurant.getAddress(), is("Busan"));
    }

    private void mockRestaurantRepository() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();


        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        restaurants.add(restaurant);

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
        given(restaurantRepository.findAll()).willReturn(restaurants);
    }

    private void mockMenuItemRepository() {
        List<MenuItem>  menuItems = new ArrayList<MenuItem>();
        menuItems.add(
                MenuItem.builder()
                        .name("Kimchi")
                        .build()
        );

        given(menuItemRepository.findByRestaurantId(1004L)).willReturn(menuItems);
    }


}