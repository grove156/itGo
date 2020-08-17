package kr.co.fastcampus.Eatgo.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CategoryTests {
    @Test
    public void creation(){
        Category category = Category.builder()
                .name("Korean Food")
                .build();

        assertThat(category.getName(), is("Korean Food"));
    }
}