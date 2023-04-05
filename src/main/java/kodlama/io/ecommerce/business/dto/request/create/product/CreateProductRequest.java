package kodlama.io.ecommerce.business.dto.request.create.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String description;
}