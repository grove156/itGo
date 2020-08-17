package kr.co.fastcampus.Eatgo.application;

import kr.co.fastcampus.Eatgo.domain.Region;
import kr.co.fastcampus.Eatgo.domain.RegionRepository;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class RegionServiceTests {

    @InjectMocks
    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @Test
    public void getRegions(){
        List<Region> regions = regionService.getRegions();
        regions.add(Region.builder().name("Seoul").build());
        when(regionRepository.findAll()).thenReturn(regions);

        Region region = regions.get(0);

        assertThat(region.getName(), is("Seoul"));
    }

    @Test
    public void addRegion(){
        Region mockRegion = Region.builder().name("Seoul").build();
        given(regionRepository.save(any())).willReturn(mockRegion);

        Region region = regionService.addRegion(mockRegion);

        assertThat(region.getName(),is("Seoul"));
    }
}

