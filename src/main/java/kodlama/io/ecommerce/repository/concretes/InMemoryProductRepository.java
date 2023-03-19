package kodlama.io.ecommerce.repository.concretes;

import kodlama.io.ecommerce.entities.concretes.Product;
import kodlama.io.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class InMemoryProductRepository implements ProductRepository {
    private List<Product> products;

    public InMemoryProductRepository() {
        products=new ArrayList<>();
    }

    @Override
    public void saveProduct(Product product) {
        products.add(product);

    }

    @Override
    public Product getProductById(int id) {
        Product tempProduct=null;
        for (Product product : products) {
            if (product.getId() == id) {
                tempProduct = product;
            }
        }
        return tempProduct;
    }

    @Override
    public List<Product> findAllProducrs() {
        return products;
    }

    @Override
    public void deleteProductById(int id) {
        products.removeIf(product -> product.getId() == id);
    }

    @Override
    public void updateProduct(int id, Product product) {
        for (int i=0;i<products.size();i++){
            if (products.get(i).getId()==id){
                products.remove(i);
                products.add(i,product);
                break;
            }
        }
    }
}
