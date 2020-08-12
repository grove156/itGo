package kr.co.fastcampus.Eatgo.domain;

import kr.co.fastcampus.Eatgo.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    public Review save(Review review);

    public List<Review> findAllByRestaurantId(Long RestaurantId);
}
