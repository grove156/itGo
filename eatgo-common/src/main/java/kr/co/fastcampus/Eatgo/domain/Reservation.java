package kr.co.fastcampus.Eatgo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long userId;

    @NotEmpty
    private String name;

    @NotEmpty
    private LocalDate date;

    @NotEmpty
    private LocalTime time;

    @NotNull
    @Min(1)
    private Integer partySize;
}
