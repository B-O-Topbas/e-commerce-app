package kodlama.io.ecommerce.business.dto.response.get.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCategoriesResponse {
    private int id;
    private String name;
}