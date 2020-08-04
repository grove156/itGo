package kr.co.fastcampus.Eatgo.interfaces;

import kr.co.fastcampus.Eatgo.domain.*;
import kr.co.fastcampus.Eatgo.domain.application.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class) //스프링을 이용해서 테스트를 진행함 @Test는 자바에서도 있슴 @SpringBootTest의 하위호환
@WebMvcTest(RestaurantController.class)//web mvc를 테스트 하기위해서 사용함 get,post등의 리퀘스트를 보내기 위해서
class RestaurantControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean//mokito를 이용간 가짜 객체 단위테스트에서 Service에 의존하지 않고 service가 우리가 정해진 값을 보내주고 우리는 controller만 테스트
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
        restaurants.add(new Restaurant(1004L,"Bob zip", "Seoul"));
        //mokito를 이용한 가짜객체 주입
        given(restaurantService.getRestaurants()).willReturn(restaurants);

        //mockMvc를 이용해서 테스트
        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1004")))
                .andExpect(content().string(containsString("\"name\":\"Bob zip\"")));
    }

    @Test
    public void detail() throws Exception {
        Restaurant restaurant1 = new Restaurant(1004L,"Bob zip","Seoul");
        restaurant1.addMenuItem(new MenuItem("Kimchi"));
        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);

        Restaurant restaurant2 = new Restaurant(2020L,"Cyber food","Seoul");
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);

        mvc.perform(get("/restaurant/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1004")))
                .andExpect(content().string(containsString("\"name\":\"Bob zip\"")))
                .andExpect(content().string(containsString("Kimchi")));

        mvc.perform(get("/restaurant/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":2020")))
                .andExpect(content().string(containsString("\"name\":\"Cyber food\"")));
    }

    @Test
    public void create() throws Exception {
        Restaurant restaurant = new Restaurant(1234L,"BeRyong","Seoul");
        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON) //바디에서 오는 컨텐츠 타입이 json파일인걸 정의함
                .content("{\"name\": \"BeRyong\", \"address\": \"Seoul\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/restaurants/1234"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());//restaurantService의 addRestaurant메소드가 실행되었는지 확인하기 위해서 mockito의 verify를 사용함
    }
}