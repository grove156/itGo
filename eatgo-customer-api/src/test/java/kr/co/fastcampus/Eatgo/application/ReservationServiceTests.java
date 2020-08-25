package kr.co.fastcampus.Eatgo.application;

import kr.co.fastcampus.Eatgo.domain.Reservation;
import kr.co.fastcampus.Eatgo.domain.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class ReservationServiceTests {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Test
    public void addReservation(){

        Long userId = 1L;
        Long restaurantId = 1004L;
        String name = "John";
        LocalDate date = LocalDate.of(2020,7,8);
        LocalTime time = LocalTime.of(18,30);
        Integer partySize = 30;

        Reservation mockReservation = Reservation.builder().name(name).build();
        given(reservationRepository.save(any(Reservation.class))).willReturn(mockReservation);

        Reservation reservation = reservationService.addReservation(restaurantId, userId, name, date, time, partySize);

        assertThat(reservation.getName(), is(name));

    }
}