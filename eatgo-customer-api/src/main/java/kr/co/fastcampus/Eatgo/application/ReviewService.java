package kr.co.fastcampus.Eatgo.application;

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

    public Review addReview(Long id, String name, String description, int score){

        Review review = Review.builder()
                .restaurantId(id)
                .name(name)
                .description(description)
                .score(score)
                .build();

        return reviewRepository.save(review);
    }

}
