package kr.co.fastcampus.Eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class MenuItem {

    @Id
    @GeneratedValue
    private Long id;

    private Long restaurantId;

    private String name;

    @Transient//DB에서는 무시하고 그냥 지나침 즉 저장하지 않음
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) //기본값이 아니면 넣어라
    private boolean isDestroyed;
}
