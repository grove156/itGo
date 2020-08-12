package kr.co.fastcampus.Eatgo.domain.application;

import kr.co.fastcampus.Eatgo.domain.Review;
import kr.co.fastcampus.Eatgo.domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ReviewServiceTests {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void addReview(){
        Review review = Review.builder()
                .name("Joker")
                .score(3)
                .description("good")
                .build();
        reviewService.addReview(1L,review);
        verify(reviewRepository).save(any());
    }
}