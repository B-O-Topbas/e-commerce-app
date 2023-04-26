package kodlama.io.ecommerce.business.rules;

import kodlama.io.ecommerce.exceptions.BrandExistsException;
import kodlama.io.ecommerce.exceptions.DescriptionException;
import kodlama.io.ecommerce.exceptions.PriceException;
import kodlama.io.ecommerce.exceptions.QuantityException;
import kodlama.io.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductBusinessRules {
    private final ProductRepository productRepository;

    public ProductBusinessRules(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void checkPrice(double price) {
        if (price <= 0) {
            throw new PriceException("The price of the product cannot be less than zero");
        }
    }

    public void checkQuantity(int quantity) {
        if (quantity < 0) {
            throw new QuantityException("The quantity of the product can not be less than zero");
        }
    }

    public void checkDescription(String description) {
        if (description.length() <= 10 || description.length() > 50) {
            throw new DescriptionException("The description of the product must be between ten and fifty characters");
        }
    }

    public void checkProductIsExists(int id) {
        if (!productRepository.existsById(id)) throw new BrandExistsException("There is no brand with this id");
    }
}