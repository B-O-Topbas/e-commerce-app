package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.exceptions.BrandExistsException;
import kodlama.io.ecommerce.exceptions.DescriptionException;
import kodlama.io.ecommerce.exceptions.PriceException;
import kodlama.io.ecommerce.exceptions.QuantityException;
import kodlama.io.ecommerce.repository.ProductRepository;
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
        checkDescription(product);
        checkQuantity(product);
        checkPrice(product);
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(int id) {
        checkBrandExists(id);
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(int id) {
        checkBrandExists(id);
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void updateProduct(int id, Product product) {
        checkDescription(product);
        checkQuantity(product);
        checkPrice(product);
        product.setId(id);
        productRepository.save(product);
    }


    //rules
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
    private void checkBrandExists(int id){
        if (!productRepository.existsById(id))throw new BrandExistsException("There is no brand with this id");
    }
}