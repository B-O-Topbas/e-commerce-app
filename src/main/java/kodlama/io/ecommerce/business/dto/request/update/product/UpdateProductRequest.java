package kodlama.io.ecommerce.business.dto.request.update.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
    private String name;
    private int stockQuantity;
    private double unirPrice;
    private String description;
    private int categoryId;
}