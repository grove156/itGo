package kr.co.fastcampus.Eatgo.application;

import kr.co.fastcampus.Eatgo.application.ReviewService;
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
        reviewService.addReview(1L,"Joker","good enough",3);
        verify(reviewRepository).save(any());
    }
}