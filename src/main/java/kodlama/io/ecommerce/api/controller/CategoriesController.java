package kodlama.io.ecommerce.api.controller;

import kodlama.io.ecommerce.business.abstracts.CategoryService;
import kodlama.io.ecommerce.business.dto.request.create.category.CreateCategoryRequest;
import kodlama.io.ecommerce.business.dto.request.update.category.UpdateCategoryRequest;
import kodlama.io.ecommerce.business.dto.response.create.category.CreateCategoryResponse;
import kodlama.io.ecommerce.business.dto.response.get.category.GetAllCategoriesResponse;
import kodlama.io.ecommerce.business.dto.response.get.category.GetCategoryResponse;
import kodlama.io.ecommerce.business.dto.response.update.category.UpdateCategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoryService categoryService;
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public ResponseEntity<List<GetAllCategoriesResponse>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetCategoryResponse> getCategoryById(@PathVariable int id){
        return new ResponseEntity<>(categoryService.getCategoryWithId(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CreateCategoryResponse> saveCategory(@RequestBody CreateCategoryRequest categoryRequest){
        return new ResponseEntity<>(categoryService.saveCategory(categoryRequest),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdateCategoryResponse> updateCategory(@PathVariable int id, @RequestBody UpdateCategoryRequest updateCategoryRequest){
        return new ResponseEntity<>(categoryService.updateCategory(id,updateCategoryRequest),HttpStatus.OK);
    }
}