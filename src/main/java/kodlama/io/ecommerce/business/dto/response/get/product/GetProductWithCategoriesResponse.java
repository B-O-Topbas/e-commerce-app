package kodlama.io.ecommerce.business.dto.response.get.product;

import kodlama.io.ecommerce.business.dto.response.get.category.GetAllCategoriesResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetProductWithCategoriesResponse {
    private int id;
    private String name;
    private int stockQuantity;
    private double unirPrice;
    private String description;
    private boolean situation;
    private List<GetAllCategoriesResponse> categoryResponses;
}