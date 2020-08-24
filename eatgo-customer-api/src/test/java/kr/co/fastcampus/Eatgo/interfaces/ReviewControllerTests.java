package kr.co.fastcampus.Eatgo.interfaces;

import kr.co.fastcampus.Eatgo.application.ReviewService;
import kr.co.fastcampus.Eatgo.domain.Review;
import kr.co.fastcampus.Eatgo.interfaces.ReviewController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void createWithValidAttribute() throws Exception {
        String token ="eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb2huIn0.aL5SFNFszqxyMILjvqYeXDCVgLa_wEmTt3GdaRGQPkc";

        given(reviewService.addReview(any(),"John","good enough",3)).willReturn(
          Review.builder()
                  .id(123L)
                  .name("Joker")
                  .score(3)
                  .description("good")
                  .restaurantId(1004L)
                  .build()
        );
        mvc.perform(post("/restaurant/1/reviews")
                .header("Authorization","Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"score\":\"3\",\"description\":\"good\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/restaurant/1/reviews/123"));

        verify(reviewService).addReview(any(), eq("John"),eq("good enough"),eq(3));
    }

    @Test
    public void createWithInvalidAttribute() throws Exception {
        mvc.perform(post("/restaurant/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":,\"score\":\"3\",\"description\":\"good\"}"))
                .andExpect(status().isBadRequest());

        verify(reviewService, never()).addReview(any(), any(), any(), any());
    }
}