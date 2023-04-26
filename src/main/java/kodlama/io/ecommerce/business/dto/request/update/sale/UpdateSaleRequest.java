package kodlama.io.ecommerce.business.dto.request.update.sale;

import kodlama.io.ecommerce.business.dto.request.create.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSaleRequest {
    private int productId;
    private int numberOfPurchases;
    private double unitPrice;
    private PaymentRequest paymentRequest;
    private LocalDateTime dateOfPurchase;
}
