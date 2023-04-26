package kodlama.io.ecommerce.business.dto.response.update.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductResponse {
    private int id;
    private String name;
    private int stockQuantity;
    private double unirPrice;
    private String description;
    private boolean situation;
}