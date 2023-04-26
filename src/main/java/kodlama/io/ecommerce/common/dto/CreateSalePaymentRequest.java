package kodlama.io.ecommerce.common.dto;

import kodlama.io.ecommerce.business.dto.request.create.PaymentRequest;
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