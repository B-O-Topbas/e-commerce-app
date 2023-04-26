package kodlama.io.ecommerce.business.dto.request.create.sale;

import kodlama.io.ecommerce.business.dto.request.create.PaymentRequest;
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
    private int numberOfPurchases;
    private double unitPrice;
    private PaymentRequest paymentRequest;
}

