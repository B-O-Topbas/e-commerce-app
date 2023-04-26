package kodlama.io.ecommerce.business.dto.request.create.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    private int id;
    private String name;
    private int stockQuantity;
    private double unirPrice;
    private String description;
    private boolean situation;
}