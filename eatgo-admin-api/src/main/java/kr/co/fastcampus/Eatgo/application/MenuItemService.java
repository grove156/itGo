package kr.co.fastcampus.Eatgo.application;

import kr.co.fastcampus.Eatgo.domain.MenuItem;
import kr.co.fastcampus.Eatgo.domain.MenuItemRepository;
import kr.co.fastcampus.Eatgo.domain.Restaurant;
import kr.co.fastcampus.Eatgo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService {

    @Autowired
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

    public List<MenuItem> getMenuItems(Long restaurantId){
        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(restaurantId);
        return menuItems;
    }
}
