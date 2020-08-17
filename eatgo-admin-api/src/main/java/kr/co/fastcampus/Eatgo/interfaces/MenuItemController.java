package kr.co.fastcampus.Eatgo.interfaces;

import kr.co.fastcampus.Eatgo.application.MenuItemService;
import kr.co.fastcampus.Eatgo.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuItemController {

    @Autowired
    MenuItemService menuItemService;

    @PatchMapping("/restaurant/{RestaurantId}/menuitems")
    public String bulkUpdate(@PathVariable Long RestaurantId, @RequestBody List<MenuItem> menuItems){
        menuItemService.bulkUpdate(RestaurantId,menuItems);
        return "";
    }
}
