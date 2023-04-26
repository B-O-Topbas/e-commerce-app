package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.request.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSaleRequest {
    private int productId;
    private int piece;
    private double unitPrice;
    private PaymentRequest paymentRequest;
}
