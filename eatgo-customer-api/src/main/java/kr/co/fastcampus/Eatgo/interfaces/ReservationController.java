package kr.co.fastcampus.Eatgo.interfaces;

import kr.co.fastcampus.Eatgo.application.ReservationService;
import kr.co.fastcampus.Eatgo.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/restaurants/{restaurantId}/reservations")
    public ResponseEntity<?> create(@PathVariable(value="restaurantId") Long restaurantId,
                                    @RequestBody Reservation resource) throws URISyntaxException {

        Long userId = 1L;
        String name = "Teset";
        LocalDate date = resource.getDate();//LocalDate.of(2020,7,8);
        LocalTime time = resource.getTime();//LocalTime.of(18,30);
        Integer partySize = resource.getPartySize();

        Reservation reservation = reservationService.addReservation(restaurantId, userId, name, date, time, partySize);

        String url = "/restaurants/1004/reservations/"+reservation.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
