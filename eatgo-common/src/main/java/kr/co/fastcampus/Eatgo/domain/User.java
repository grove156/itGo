package kr.co.fastcampus.Eatgo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String email;

    @NotNull
    private String name;

    private Long restaurantId;

    @NotNull
    private Long level;

    private String password;

    public boolean isAdmin(){
        if(level>=3){
            return true;
        }else{
            return false;
        }
    }

    public void setRestaurantId(Long restaurantId){
        this.restaurantId = restaurantId;
        this.level = 2L;
    }

    public boolean isRestaurantOwner() {
        if(level==2) return true;
        else return false;
    }
}
