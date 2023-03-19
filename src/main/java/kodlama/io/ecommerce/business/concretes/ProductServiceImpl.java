package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.entities.concretes.Product;
import kodlama.io.ecommerce.exceptions.DescriptionException;
import kodlama.io.ecommerce.exceptions.PriceException;
import kodlama.io.ecommerce.exceptions.QuantityException;
import kodlama.io.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void saveProduct(Product product) {
        checkPrice(product);
        checkQuantity(product);
        checkDescription(product);
        productRepository.saveProduct(product);
    }

    @Override
    public void deleteProductById(int id) {
        productRepository.deleteProductById(id);
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllProducrs();
    }

    @Override
    public void updateProduct(int id, Product product) {
        checkPrice(product);
        checkQuantity(product);
        checkDescription(product);
        productRepository.updateProduct(id, product);
    }

    private void checkPrice(Product product) {
        if (product.getPrice() <= 0) {
            throw new PriceException("The price of the product cannot be less than zero");
        }
    }

    private void checkQuantity(Product product) {
        if (product.getQuantity() < 0) {
            throw new QuantityException("The quantity of the product can not be less than zero");
        }
    }

    private void checkDescription(Product product) {
        if (product.getDescription().length() <= 10 || product.getDescription().length() > 50) {
            throw new DescriptionException("The description of the product must be between ten and fifty characters");
        }
    }
}