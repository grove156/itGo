package kr.co.fastcampus.Eatgo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findAll();

    List<Restaurant> findByAddressContaining(String region);

    List<Restaurant> findByAddressContainingAndCategoryId(String region, long categoryId);

    Optional<Restaurant> findById(Long id);

    Restaurant save(Restaurant restaurant);
}
