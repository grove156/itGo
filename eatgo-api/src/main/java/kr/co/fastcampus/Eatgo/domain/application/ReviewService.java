package kr.co.fastcampus.Eatgo.domain.application;

import kr.co.fastcampus.Eatgo.domain.Review;
import kr.co.fastcampus.Eatgo.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

//    public ReviewService(ReviewRepository reviewRepository){
//        this.reviewRepository = reviewRepository;
//    }

    public Review addReview(Long id, Review review){
        review.setRestaurantId(id);
        return reviewRepository.save(review);
    }

}
