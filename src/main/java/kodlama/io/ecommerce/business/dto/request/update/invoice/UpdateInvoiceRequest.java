package kodlama.io.ecommerce.business.dto.request.update.invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {
    private String cardHolder;
    private String productName;
    private double unitPrice;
    private int numberOfPurchases;
    private LocalDateTime dateOfPurchase;
}
