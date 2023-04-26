package kodlama.io.ecommerce.business.dto.response.get.invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetInvoiceResponse {
    private int id;
    private String cardHolder;
    private String productName;
    private double unitPrice;
    private int numberOfPurchases;
    private LocalDateTime dateOfPurchase;
    private double totalPrice;
}
