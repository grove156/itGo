package kr.co.fastcampus.Eatgo.application;

import kr.co.fastcampus.Eatgo.domain.Reservation;
import kr.co.fastcampus.Eatgo.domain.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public Reservation addReservation(Long restaurantId, Long userId, String name, LocalDate date, LocalTime time, Integer partySize) {

        //TODO:실제 구현하기
        Reservation reservation = Reservation.builder()
                .userId(userId)
                .id(restaurantId)
                .name(name)
                .date(date)
                .time(time)
                .partySize(partySize)
                .build();
        Reservation newReservation = reservationRepository.save(reservation);
        return newReservation;
    }
}
