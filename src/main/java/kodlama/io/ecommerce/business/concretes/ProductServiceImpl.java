package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.CategoryService;
import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.business.dto.request.update.product.ChangeProductSituationRequest;
import kodlama.io.ecommerce.business.dto.request.create.product.CreateProductRequest;
import kodlama.io.ecommerce.business.dto.response.update.product.UpdateProductResponse;
import kodlama.io.ecommerce.business.dto.response.create.product.CreateProductResponse;
import kodlama.io.ecommerce.business.dto.response.get.category.GetAllCategoriesResponse;
import kodlama.io.ecommerce.business.dto.response.get.product.GetAllProductsResponse;
import kodlama.io.ecommerce.business.dto.response.get.product.GetAllProductsWithCategoriesResponse;
import kodlama.io.ecommerce.business.dto.response.get.product.GetProductResponse;
import kodlama.io.ecommerce.business.dto.response.get.product.GetProductWithCategoriesResponse;
import kodlama.io.ecommerce.business.dto.request.update.product.UpdateProductRequest;
import kodlama.io.ecommerce.business.rules.ProductBusinessRules;
import kodlama.io.ecommerce.entities.Category;
import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.repository.CategoryRepository;
import kodlama.io.ecommerce.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final ProductBusinessRules rules;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper
            , CategoryService categoryService, CategoryRepository categoryRepository, ProductBusinessRules rules) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.rules = rules;
    }

    @Override
    public CreateProductResponse saveProduct(CreateProductRequest createProductRequest) {
        rules.checkDescription(createProductRequest.getDescription());
        rules.checkQuantity(createProductRequest.getStockQuantity());
        rules.checkPrice(createProductRequest.getUnirPrice());
        Product product = modelMapper.map(createProductRequest, Product.class);
        productRepository.save(product);
        return modelMapper.map(product, CreateProductResponse.class);
    }

    @Override
    public void deleteProductById(int id) {
        rules.checkProductIsExists(id);
        productRepository.deleteById(id);
    }

    @Override
    public GetProductResponse getProductById(int id) {
        rules.checkProductIsExists(id);
        Product product = productRepository.findById(id).orElseThrow();
        return modelMapper.map(product, GetProductResponse.class);
    }

    @Override
    public List<GetAllProductsResponse> getAllProducts(String situation) {
        List<Product> products = productRepository.findAll();
        boolean check = switch (situation) {
            case "active" -> true;
            case "passive" -> false;
            default -> throw new IllegalStateException("Unexpected value: " + situation);
        };
        return products.stream()
                .filter(product -> product.isSituation() == check)
                .map(product -> modelMapper.map(product, GetAllProductsResponse.class)).toList();
    }

    @Override
    public GetProductResponse changeSituation(int id, ChangeProductSituationRequest situationRequest) {
        rules.checkProductIsExists(id);
        Product product = productRepository.findById(id).orElseThrow();
        product.setSituation(situationRequest.isStiutation());
        productRepository.save(product);
        return modelMapper.map(product, GetProductResponse.class);
    }

    @Override
    public UpdateProductResponse updateProduct(int id, UpdateProductRequest updateProductRequest) {
        rules.checkProductIsExists(id);
        rules.checkDescription(updateProductRequest.getDescription());
        rules.checkQuantity(updateProductRequest.getStockQuantity());
        rules.checkPrice(updateProductRequest.getUnirPrice());
        Product product = productRepository.findById(id).orElseThrow();
        Category category = categoryRepository.findById(updateProductRequest.getCategoryId()).orElseThrow();
        product.setDescription(updateProductRequest.getDescription());
        product.setName(updateProductRequest.getName());
        product.setStockQuantity(updateProductRequest.getStockQuantity());
        product.setUnitPrice(updateProductRequest.getUnirPrice());
        product.getCategories().add(category);
        productRepository.save(product);
        return modelMapper.map(product, UpdateProductResponse.class);
    }

    @Override
    public GetProductWithCategoriesResponse getProductWithCategories(int id) {
        Product product = productRepository.findById(id).orElseThrow();
        List<GetAllCategoriesResponse> productWithCategoriesResponses =
                product.getCategories()
                        .stream()
                        .map(category -> modelMapper.map(category, GetAllCategoriesResponse.class)).toList();
        GetProductWithCategoriesResponse response = modelMapper.map(product, GetProductWithCategoriesResponse.class);
        response.setCategoryResponses(productWithCategoriesResponses);
        return response;
    }

    @Override
    public List<GetAllProductsWithCategoriesResponse> getProductsWithCategories() {
        List<Product> products = productRepository.findAll();
        List<GetAllProductsWithCategoriesResponse> getAllProductsWithCategoriesResponse = new ArrayList<>();
        for (Product p : products) {
            List<GetAllCategoriesResponse> getAllCategoriesResponse = new ArrayList<>();
            GetAllProductsWithCategoriesResponse getAllProductsWithCategoriesResponse1;
            for (Category c : p.getCategories()) {
                getAllCategoriesResponse.add(modelMapper.map(c, GetAllCategoriesResponse.class));
            }
            getAllProductsWithCategoriesResponse1 = modelMapper.map(p, GetAllProductsWithCategoriesResponse.class);
            getAllProductsWithCategoriesResponse1.setCategoryResponses(getAllCategoriesResponse);
            getAllProductsWithCategoriesResponse.add(getAllProductsWithCategoriesResponse1);
        }
        return getAllProductsWithCategoriesResponse;
    }
}