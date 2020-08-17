package kr.co.fastcampus.Eatgo.application;

import kr.co.fastcampus.Eatgo.domain.Category;
import kr.co.fastcampus.Eatgo.domain.CategoryRepository;
import kr.co.fastcampus.Eatgo.domain.Region;
import kr.co.fastcampus.Eatgo.domain.RegionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CategoryServiceTests {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void getRegions(){
        List<Category> categories = categoryService.getCategory();
        categories.add(Category.builder().name("Korean Food").build());
        when(categoryRepository.findAll()).thenReturn(categories);

        Category category = categories.get(0);

        assertThat(category.getName(), is("Korean Food"));
    }

    @Test
    public void addRegion(){
        Category mockCategory = Category.builder().name("Korean Food").build();
        given(categoryRepository.save(any())).willReturn(mockCategory);

        Category category = categoryService.addCategory(mockCategory);

        assertThat(category.getName(),is("Korean Food"));
    }
}