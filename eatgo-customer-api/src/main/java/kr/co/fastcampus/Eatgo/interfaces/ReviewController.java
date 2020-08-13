package kr.co.fastcampus.Eatgo.interfaces;

import kr.co.fastcampus.Eatgo.application.ReviewService;
import kr.co.fastcampus.Eatgo.domain.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("restaurant/{RestaurantId}/reviews")
    public ResponseEntity<Object> create(@PathVariable Long RestaurantId, @Valid @RequestBody Review resource) throws URISyntaxException {

       Review review = reviewService.addReview(RestaurantId,resource);

        URI location = new URI("/restaurant/"+RestaurantId+"/reviews/"+review.getId());
        return ResponseEntity.created(location).body("{}");
    }
}
