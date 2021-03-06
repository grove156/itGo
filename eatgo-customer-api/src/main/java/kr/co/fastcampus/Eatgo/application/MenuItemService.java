package kr.co.fastcampus.Eatgo.application;

import kr.co.fastcampus.Eatgo.domain.MenuItem;
import kr.co.fastcampus.Eatgo.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository){
        this.menuItemRepository = menuItemRepository;
    }

    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {

        for(MenuItem menuItem : menuItems){
            if(menuItem.isDestroyed() == true){
                menuItemRepository.deleteById(menuItem.getId());
                continue;
            }

            menuItem.setRestaurantId(restaurantId);
            menuItemRepository.save(menuItem);
        }
    }
}
