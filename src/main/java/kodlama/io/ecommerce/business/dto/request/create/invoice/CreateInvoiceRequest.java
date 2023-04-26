package kodlama.io.ecommerce.business.dto.request.create.invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {
    private String cardHolder;
    private String productName;
    private double unitPrice;
    private int  numberOfPurchases;
    private LocalDateTime dateOfPurchase;
}

