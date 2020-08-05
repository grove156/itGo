package kr.co.fastcampus.Eatgo.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
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
    private String name;

    @NonNull
    private String address;

    @Transient //DB에서 처리하지 않고 통과하게 만드는 어노테이션
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

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
}

