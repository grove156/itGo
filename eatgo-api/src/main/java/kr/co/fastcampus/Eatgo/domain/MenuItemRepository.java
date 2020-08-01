package kr.co.fastcampus.Eatgo.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface MenuItemRepository {
    List<MenuItem> findByRestaurantId(Long RestaurantId);
}
