package kodlama.io.ecommerce.business.dto.response.create.sale;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSaleResponse {
    private int id;
    private int productId;
    private double unitPrice;
    private int numberOfPurchases;
    private double totalPrice;
    private LocalDateTime dateOfPurchase;
}

