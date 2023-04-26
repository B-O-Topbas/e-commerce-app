package kodlama.io.ecommerce.api.controller;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.business.dto.request.create.product.CreateProductRequest;
import kodlama.io.ecommerce.business.dto.response.update.product.UpdateProductResponse;
import kodlama.io.ecommerce.business.dto.response.create.product.CreateProductResponse;
import kodlama.io.ecommerce.business.dto.response.get.product.GetAllProductsResponse;
import kodlama.io.ecommerce.business.dto.response.get.product.GetAllProductsWithCategoriesResponse;
import kodlama.io.ecommerce.business.dto.response.get.product.GetProductResponse;
import kodlama.io.ecommerce.business.dto.response.get.product.GetProductWithCategoriesResponse;
import kodlama.io.ecommerce.business.dto.request.update.product.UpdateProductRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductService productService;
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/state/{situation}")
    public ResponseEntity<List<GetAllProductsResponse>> getAll(@PathVariable String situation){
        return new ResponseEntity<>(productService.getAllProducts(situation), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CreateProductResponse> saveProduct(@RequestBody CreateProductRequest productRequest){
        return new ResponseEntity<>(productService.saveProduct(productRequest),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductPyId(@PathVariable int id){
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetProductResponse> getProductById(@PathVariable int id){
        return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdateProductResponse> updateProduct(@PathVariable int id
            , @RequestBody UpdateProductRequest updateProductRequest){
        return new ResponseEntity<>(productService.updateProduct(id,updateProductRequest),HttpStatus.OK);
    }
    @GetMapping("/with-categories/{id}")
    public ResponseEntity<GetProductWithCategoriesResponse> getProductWithCategories(@PathVariable int id){
        return new ResponseEntity<>(productService.getProductWithCategories(id),HttpStatus.OK);
    }
    @GetMapping("/get-all-with-categories")
    public ResponseEntity<List<GetAllProductsWithCategoriesResponse>> getAllProductsWithC(){
        return new ResponseEntity<>(productService.getProductsWithCategories(),HttpStatus.OK);
    }
}