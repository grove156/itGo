package kr.co.fastcampus.Eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @NotEmpty
    private String name;

    @NonNull //@NonNull은 lombok에서 쓰는 어노테이션으로 requiredArgs 쓸때 쓴다
    @NotEmpty //NotEmpty는 @Valid를 이용하기 위해서 사용하는 것으로 NotNull은 빈문자열""을 허용하지만 NotEmpty는 빈문자열""을 허용하지 않음
    private String address;

    @Transient //DB에서 처리하지 않고 통과하게 만드는 어노테이션
    @JsonInclude(JsonInclude.Include.NON_NULL) //json으로 패싱할때 이부분이 null이면 보내지 않음
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    @Transient //DB에서 처리하지 않고 통과하게 만드는 어노테이션
    @JsonInclude(JsonInclude.Include.NON_NULL) //json으로 패싱할때 이부분이 null이면 보내지 않음
    private List<Review> reviews;

    public List<MenuItem> getMenuItems(){
        return menuItems;
    }

    public String getInformation(){
        return name+" in "+address;
    }

    public void setInformation(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void addMenuItem(MenuItem menuItem){
        menuItems.add(menuItem);
    }

    public void setMenuItem(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<MenuItem>();
        for(MenuItem menuItem: menuItems){
            addMenuItem(menuItem);
        }
    }

    public void setReviews(List<Review> review) {
        this.reviews = new ArrayList<Review>(review);
    }
}

