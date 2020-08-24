package kr.co.fastcampus.Eatgo.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.fastcampus.Eatgo.application.ReviewService;
import kr.co.fastcampus.Eatgo.domain.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @PostMapping("restaurant/{RestaurantId}/reviews")//Authentication을 필터를 만들어주었기 때문에 가능
    public ResponseEntity<Object> create(Authentication authentication, @PathVariable Long RestaurantId, @Valid @RequestBody Review resource) throws URISyntaxException {
        Claims claims = (Claims) authentication.getPrincipal();//Claims로 형변환
        String name = claims.get("name",String.class);//반환받을 타입을 String.class로 설정
        Integer score = resource.getScore();
        String description = resource.getDescription();
        Review review = reviewService.addReview(RestaurantId,name, description, score);

        URI location = new URI("/restaurant/"+RestaurantId+"/reviews/"+review.getId());
        return ResponseEntity.created(location).body("{}");
    }
}
