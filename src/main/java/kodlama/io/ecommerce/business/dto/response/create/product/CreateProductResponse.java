package kodlama.io.ecommerce.business.dto.response.create.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductResponse {
    private int id;
    private String name;
    private int stockQuantity;
    private double unirPrice;
    private String description;
    private boolean situation;
}