package kodlama.io.ecommerce.repository.abstracts;

import kodlama.io.ecommerce.entities.concretes.Product;

import java.util.List;

public interface ProductRepository {
    void saveProduct(Product product);
    Product getProductById(int id);
    List<Product> findAllProducrs();
    void deleteProductById(int id);
    void updateProduct(int id,Product product);
}