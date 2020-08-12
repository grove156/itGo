package kr.co.fastcampus.Eatgo.interfaces;

import kr.co.fastcampus.Eatgo.domain.Review;
import kr.co.fastcampus.Eatgo.domain.application.ReviewService;
import kr.co.fastcampus.Eatgo.interfaces.ReviewController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
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
        given(reviewService.addReview(any(),any())).willReturn(
          Review.builder()
                  .id(123L)
                  .name("Joker")
                  .score(3)
                  .description("good")
                  .restaurantId(1004L)
                  .build()
        );
        mvc.perform(post("/restaurant/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Joker\",\"score\":\"3\",\"description\":\"good\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/restaurant/1/reviews/123"));

        verify(reviewService).addReview(any(), any());
    }

    @Test
    public void createWithInvalidAttribute() throws Exception {
        mvc.perform(post("/restaurant/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":,\"score\":\"3\",\"description\":\"good\"}"))
                .andExpect(status().isBadRequest());

        verify(reviewService, never()).addReview(any(), any());
    }
}