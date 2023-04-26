package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.request.create.product.CreateProductRequest;
import kodlama.io.ecommerce.business.dto.request.update.product.ChangeProductSituationRequest;
import kodlama.io.ecommerce.business.dto.response.update.product.UpdateProductResponse;
import kodlama.io.ecommerce.business.dto.response.create.product.CreateProductResponse;
import kodlama.io.ecommerce.business.dto.response.get.product.GetAllProductsResponse;
import kodlama.io.ecommerce.business.dto.response.get.product.GetAllProductsWithCategoriesResponse;
import kodlama.io.ecommerce.business.dto.response.get.product.GetProductResponse;
import kodlama.io.ecommerce.business.dto.response.get.product.GetProductWithCategoriesResponse;
import kodlama.io.ecommerce.business.dto.request.update.product.UpdateProductRequest;

import java.util.List;

public interface ProductService {
    CreateProductResponse saveProduct(CreateProductRequest createProductRequest);
    void deleteProductById(int id);
    GetProductResponse getProductById(int id);

    List<GetAllProductsResponse> getAllProducts(String situation);

    GetProductResponse changeSituation(int id, ChangeProductSituationRequest stiuationRequest);

    UpdateProductResponse updateProduct(int id, UpdateProductRequest updateProductRequest);
    GetProductWithCategoriesResponse getProductWithCategories(int id);
    List<GetAllProductsWithCategoriesResponse> getProductsWithCategories();
}