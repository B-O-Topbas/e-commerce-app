package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.business.dto.request.create.product.CreateProductRequest;
import kodlama.io.ecommerce.business.dto.request.update.product.UpdateProductResponse;
import kodlama.io.ecommerce.business.dto.response.create.product.CreateProductResponse;
import kodlama.io.ecommerce.business.dto.response.get.category.GetAllCategoriesResponse;
import kodlama.io.ecommerce.business.dto.response.get.product.GetAllProductsResponse;
import kodlama.io.ecommerce.business.dto.response.get.product.GetAllProductsWithCategoriesResponse;
import kodlama.io.ecommerce.business.dto.response.get.product.GetProductResponse;
import kodlama.io.ecommerce.business.dto.response.get.product.GetProductWithCategoriesResponse;
import kodlama.io.ecommerce.business.dto.response.update.product.UpdateProductRequest;
import kodlama.io.ecommerce.entities.Category;
import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.exceptions.BrandExistsException;
import kodlama.io.ecommerce.exceptions.DescriptionException;
import kodlama.io.ecommerce.exceptions.PriceException;
import kodlama.io.ecommerce.exceptions.QuantityException;
import kodlama.io.ecommerce.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CreateProductResponse saveProduct(CreateProductRequest createProductRequest) {
        checkDescription(createProductRequest.getDescription());
        checkQuantity(createProductRequest.getQuantity());
        checkPrice(createProductRequest.getPrice());
        Product product=modelMapper.map(createProductRequest, Product.class);
        productRepository.save(product);
        return modelMapper.map(product, CreateProductResponse.class);
    }

    @Override
    public void deleteProductById(int id) {
        checkBrandExists(id);
        productRepository.deleteById(id);
    }

    @Override
    public GetProductResponse getProductById(int id) {
        checkBrandExists(id);
        Product product=productRepository.findById(id).orElseThrow();
        return modelMapper.map(product, GetProductResponse.class);
    }

    @Override
    public List<GetAllProductsResponse> getAllProducts() {
        List<Product> products=productRepository.findAll();

        return products.stream()
                .map(product -> modelMapper.map(product, GetAllProductsResponse.class)).toList();
    }

    @Override
    public UpdateProductResponse updateProduct(int id, UpdateProductRequest updateProductRequest) {
        checkDescription(updateProductRequest.getDescription());
        checkQuantity(updateProductRequest.getQuantity());
        checkPrice(updateProductRequest.getPrice());
        Product product=productRepository.findById(id).orElseThrow();
        product.setDescription(updateProductRequest.getDescription());
        product.setName(updateProductRequest.getName());
        product.setQuantity(updateProductRequest.getQuantity());
        product.setPrice(updateProductRequest.getPrice());
        productRepository.save(product);
        return modelMapper.map(product, UpdateProductResponse.class);
    }

    @Override
    public GetProductWithCategoriesResponse getProductWithCategories(int id) {
        Product product=productRepository.findById(id).orElseThrow();
        List<GetAllCategoriesResponse> productWithCategoriesResponses=
                product.getCategories()
                        .stream()
                        .map(category -> modelMapper.map(category,GetAllCategoriesResponse.class)).toList();
        GetProductWithCategoriesResponse response=modelMapper.map(product,GetProductWithCategoriesResponse.class);
        response.setCategoryResponses(productWithCategoriesResponses);
        return response;
    }

    @Override
    public List<GetAllProductsWithCategoriesResponse> getProductsWithCategories() {
        List<Product> products=productRepository.findAll();
        List<GetAllProductsWithCategoriesResponse> getAllProductsWithCategoriesResponse=new ArrayList<>();
        for(Product p:products){
            List<GetAllCategoriesResponse> getAllCategoriesResponse=new ArrayList<>();
            GetAllProductsWithCategoriesResponse getAllProductsWithCategoriesResponse1;
            for (Category c:p.getCategories()){
                getAllCategoriesResponse.add(modelMapper.map(c,GetAllCategoriesResponse.class));
            }
            getAllProductsWithCategoriesResponse1=modelMapper.map(p,GetAllProductsWithCategoriesResponse.class);
            getAllProductsWithCategoriesResponse1.setCategoryResponses(getAllCategoriesResponse);
            getAllProductsWithCategoriesResponse.add(getAllProductsWithCategoriesResponse1);
        }
//        List<GetAllCategoriesResponse> categoriesResponses=
//                products.forEach(product -> product.getCategories()
//                        .stream()
//                        .map(category -> modelMapper.map(category,GetAllCategoriesResponse.class)).toList());
        return getAllProductsWithCategoriesResponse;
    }


    //rules
    private void checkPrice(double price) {
        if (price <= 0) {
            throw new PriceException("The price of the product cannot be less than zero");
        }
    }

    private void checkQuantity(int quantity) {
        if (quantity < 0) {
            throw new QuantityException("The quantity of the product can not be less than zero");
        }
    }

    private void checkDescription(String description) {
        if (description.length() <= 10 || description.length() > 50) {
            throw new DescriptionException("The description of the product must be between ten and fifty characters");
        }
    }
    private void checkBrandExists(int id){
        if (!productRepository.existsById(id))throw new BrandExistsException("There is no brand with this id");
    }
}