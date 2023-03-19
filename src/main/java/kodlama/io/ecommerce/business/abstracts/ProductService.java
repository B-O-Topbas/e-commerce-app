package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    void saveProduct(Product product);
    void deleteProductById(int id);
    Product getProductById(int id);
    List<Product> getAllProducts();
    void updateProduct(int id,Product product);
}