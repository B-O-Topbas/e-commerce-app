package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.CategoryService;
import kodlama.io.ecommerce.business.dto.request.create.category.CreateCategoryRequest;
import kodlama.io.ecommerce.business.dto.request.update.category.UpdateCategoryRequest;
import kodlama.io.ecommerce.business.dto.response.create.category.CreateCategoryResponse;
import kodlama.io.ecommerce.business.dto.response.get.category.GetAllCategoriesResponse;
import kodlama.io.ecommerce.business.dto.response.get.category.GetCategoryResponse;
import kodlama.io.ecommerce.business.dto.response.update.category.UpdateCategoryResponse;
import kodlama.io.ecommerce.entities.Category;
import kodlama.io.ecommerce.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
private final CategoryRepository categoryRepository;
private final ModelMapper modelMapper;
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public CreateCategoryResponse saveCategory(CreateCategoryRequest createCategoryRequest) {
        Category category=modelMapper.map(createCategoryRequest,Category.class);
        categoryRepository.save(category);
        return modelMapper.map(category,CreateCategoryResponse.class);
    }
    @Override
    public GetCategoryResponse getCategoryWithId(int id) {
        Category category=categoryRepository.findById(id).orElseThrow();
        return modelMapper.map(category,GetCategoryResponse.class);
    }
    @Override
    public List<GetAllCategoriesResponse> getAllCategories() {
        List<Category> categories=categoryRepository.findAll();
       return categories.stream()
                .map(category -> modelMapper.map(category,GetAllCategoriesResponse.class)).toList();
    }
    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
    @Override
    public UpdateCategoryResponse updateCategory(int id, UpdateCategoryRequest updateCategoryRequest) {
        Category category=categoryRepository.findById(id).orElseThrow();
        category.setName(updateCategoryRequest.getName());
        categoryRepository.save(category);
        return modelMapper.map(category, UpdateCategoryResponse.class);
    }
}