package kodlama.io.ecommerce.adapter;

import kodlama.io.ecommerce.business.abstracts.PaymentService;
import kodlama.io.ecommerce.business.dto.request.create.payment.CreatePaymentRequest;
import kodlama.io.ecommerce.business.dto.request.update.payment.UpdatePaymentRequest;
import kodlama.io.ecommerce.business.dto.response.create.payment.CreatePaymentResponse;
import kodlama.io.ecommerce.business.dto.response.get.payment.GetAllPaymentsResponse;
import kodlama.io.ecommerce.business.dto.response.get.payment.GetPaymentResponse;
import kodlama.io.ecommerce.business.dto.response.update.payment.UpdatePaymentResponse;
import kodlama.io.ecommerce.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapper mapper;
    private final PostService postService;
    @Override
    public CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest) {
        return null;
    }

    @Override
    public UpdatePaymentResponse update(UpdatePaymentRequest updatePaymentRequest) {
        return null;
    }

    @Override
    public GetPaymentResponse getByI(int id) {
        return null;
    }

    @Override
    public List<GetAllPaymentsResponse> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
