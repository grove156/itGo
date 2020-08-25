package kr.co.fastcampus.Eatgo.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.fastcampus.Eatgo.application.ReservationService;
import kr.co.fastcampus.Eatgo.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/restaurants/{restaurantId}/reservations")
    public List<Reservation> list(Authentication authentication){

        Claims claims = (Claims)authentication.getPrincipal();
        Long restaurantId = claims.get("restaurantId",Long.class);

        List<Reservation> reservations = reservationService.getReservations(restaurantId);

        return reservations;
    }
}