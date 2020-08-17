package kr.co.fastcampus.Eatgo.application;

import kr.co.fastcampus.Eatgo.domain.Category;
import kr.co.fastcampus.Eatgo.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    public Category addCategory(Category category) {
        Category newCategory = categoryRepository.save(category);
        return newCategory;
    }
}
