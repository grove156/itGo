package kr.co.fastcampus.Eatgo.interfaces;

import kr.co.fastcampus.Eatgo.application.ReservationService;
import kr.co.fastcampus.Eatgo.domain.Reservation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
class ReservationControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    private String token ="eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb2huIn0.aL5SFNFszqxyMILjvqYeXDCVgLa_wEmTt3GdaRGQPkc";

    @Test
    public void create() throws Exception {

        Reservation mockReservation = Reservation.builder().id(12L).build();

        given(reservationService.addReservation(any(),any(),any(),any(),any(),any()))
            .willReturn(mockReservation);

        mvc.perform(get("/restaurants/1/reservations")
                .header("Authorization","Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"date\": \"2020-7-8\", " +
                        "\"time\": \"18:30\", " +
                        "\"partySize\": 30 " +
                        "}"))
                .andExpect(status().isCreated());

        Long userId = 1L;
        Long restaurantId = 1004L;
        String name = "John";
        LocalDate date = LocalDate.of(2020,7,8);
        LocalTime time = LocalTime.of(18,30);
        Integer partySize = 30;


        verify(reservationService).addReservation(eq(restaurantId), eq(userId), eq(name), eq(date), eq(time), eq(partySize));
    }
}