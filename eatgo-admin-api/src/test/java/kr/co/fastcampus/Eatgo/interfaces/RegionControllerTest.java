package kr.co.fastcampus.Eatgo.interfaces;

import kr.co.fastcampus.Eatgo.application.RegionService;
import kr.co.fastcampus.Eatgo.domain.Region;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@WebMvcTest(RegionController.class)
@ExtendWith(SpringExtension.class)
class RegionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RegionService regionService;

    @BeforeEach //AOP? 테스트 할때 테스트 하기전에 항상 먼저 실행됨
    public void setUp() {
        MockitoAnnotations.initMocks(this); //목 객체 할당 @Mock 이 붙은 객체를 초기화 시켜줌
    }

    @Test
    public void list() throws Exception {
        List<Region> regions = new ArrayList<>();
        regions.add(Region.builder().name("Seoul city Inhun-dong").build());

        given(regionService.getRegions()).willReturn(regions);

        mvc.perform(get("/regions"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Seoul")));
    }



    @Test
    public void create() throws Exception {

       // given(regionService.addRegion(any())).willReturn(any());

        mvc.perform(post("/regions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Seoul\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("{}"));

        verify(regionService).addRegion(any());
    }
}