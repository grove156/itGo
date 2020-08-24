package kr.co.fastcampus.Eatgo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Review {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    //@NotNull 인티저나 숫자의 경우 @NotEmpty를 사용할수 없음
    @Max(value=5)
    @Min(value=0)
    private int score;

    @NotEmpty
    private String description;

    private Long restaurantId;
}
