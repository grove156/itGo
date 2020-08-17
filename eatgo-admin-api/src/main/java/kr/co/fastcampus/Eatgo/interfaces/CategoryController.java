package kr.co.fastcampus.Eatgo.interfaces;

import kr.co.fastcampus.Eatgo.application.CategoryService;
import kr.co.fastcampus.Eatgo.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> list(){

        return categoryService.getCategory();
    }

    @PostMapping("/categories")
    public ResponseEntity<Object> create(@RequestBody Category resource) throws URISyntaxException {

       Category category = categoryService.addCategory(resource);

        String url = "category/";
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
