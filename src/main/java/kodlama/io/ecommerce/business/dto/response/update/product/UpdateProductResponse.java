package kodlama.io.ecommerce.business.dto.request.update.product;

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
    private int quantity;
    private double price;
    private String description;
}