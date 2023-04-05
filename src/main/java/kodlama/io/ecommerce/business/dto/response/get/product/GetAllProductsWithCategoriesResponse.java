package kodlama.io.ecommerce.business.dto.response.get.product;

import kodlama.io.ecommerce.business.dto.response.get.category.GetAllCategoriesResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductsWithCategoriesResponse {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String description;
    private List<GetAllCategoriesResponse> categoryResponses;
}