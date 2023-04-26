package kodlama.io.ecommerce.business.dto.request;

import kodlama.io.ecommerce.business.dto.request.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSalePaymentRequest extends PaymentRequest {
    private double totalPrice;
}