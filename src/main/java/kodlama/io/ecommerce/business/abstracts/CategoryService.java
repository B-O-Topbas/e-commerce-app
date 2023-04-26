package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.request.create.category.CreateCategoryRequest;
import kodlama.io.ecommerce.business.dto.request.update.category.UpdateCategoryRequest;
import kodlama.io.ecommerce.business.dto.response.create.category.CreateCategoryResponse;
import kodlama.io.ecommerce.business.dto.response.get.category.GetAllCategoriesResponse;
import kodlama.io.ecommerce.business.dto.response.get.category.GetCategoryResponse;
import kodlama.io.ecommerce.business.dto.response.update.category.UpdateCategoryResponse;

import java.util.List;

public interface CategoryService {
    CreateCategoryResponse saveCategory(CreateCategoryRequest createCategoryRequest);
    GetCategoryResponse getCategoryWithId(int id);
    List<GetAllCategoriesResponse> getAllCategories();
    void deleteCategory(int id);
   UpdateCategoryResponse updateCategory(int id, UpdateCategoryRequest updateCategoryRequest);
}