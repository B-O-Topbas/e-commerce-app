package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.request.create.payment.CreatePaymentRequest;
import kodlama.io.ecommerce.common.dto.CreateSalePaymentRequest;
import kodlama.io.ecommerce.business.dto.request.update.payment.UpdatePaymentRequest;
import kodlama.io.ecommerce.business.dto.response.create.payment.CreatePaymentResponse;
import kodlama.io.ecommerce.business.dto.response.get.payment.GetAllPaymentsResponse;
import kodlama.io.ecommerce.business.dto.response.get.payment.GetPaymentResponse;
import kodlama.io.ecommerce.business.dto.response.update.payment.UpdatePaymentResponse;

import java.util.List;

public interface PaymentService {
    CreatePaymentResponse add(CreatePaymentRequest  createPaymentRequest);
    UpdatePaymentResponse update(int id,UpdatePaymentRequest updatePaymentRequest);
    GetPaymentResponse getById(int id);
    List<GetAllPaymentsResponse> getAll();
    void delete(int id);

    void proccesSalePayment(CreateSalePaymentRequest createSalePaymentRequest);
}
