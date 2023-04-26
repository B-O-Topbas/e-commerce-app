package kodlama.io.ecommerce.business.dto.response.update.invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceResponse {
    private int id;
    private String cardHolder;
    private String productName;
    private double unitPrice;
    private int numberOfPurchases;
    private LocalDateTime dateOfPurchase;
    private double totalPrice;
}
