package kodlama.io.ecommerce.business.dto.request.create.payment;

import kodlama.io.ecommerce.business.dto.request.create.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest extends PaymentRequest {
    private double balance;
}