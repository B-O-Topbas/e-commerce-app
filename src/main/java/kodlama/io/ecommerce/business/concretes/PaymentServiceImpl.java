package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.PaymentService;
import kodlama.io.ecommerce.business.abstracts.PosService;
import kodlama.io.ecommerce.business.dto.request.create.payment.CreatePaymentRequest;
import kodlama.io.ecommerce.business.rules.PaymentBusinesRules;
import kodlama.io.ecommerce.common.dto.CreateSalePaymentRequest;
import kodlama.io.ecommerce.business.dto.request.update.payment.UpdatePaymentRequest;
import kodlama.io.ecommerce.business.dto.response.create.payment.CreatePaymentResponse;
import kodlama.io.ecommerce.business.dto.response.get.payment.GetAllPaymentsResponse;
import kodlama.io.ecommerce.business.dto.response.get.payment.GetPaymentResponse;
import kodlama.io.ecommerce.business.dto.response.update.payment.UpdatePaymentResponse;
import kodlama.io.ecommerce.entities.Payment;
import kodlama.io.ecommerce.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapper mapper;
    private final PosService posService;
    private final PaymentBusinesRules businesRules;

    public PaymentServiceImpl(PaymentRepository repository, ModelMapper mapper, PosService posService, PaymentBusinesRules businesRules) {
        this.repository = repository;
        this.mapper = mapper;
        this.posService = posService;
        this.businesRules = businesRules;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest) {
        businesRules.checkIfCardExists(createPaymentRequest.getCardNumber());
        Payment payment = mapper.map(createPaymentRequest, Payment.class);
        payment.setId(0);
        repository.save(payment);
        return mapper.map(payment, CreatePaymentResponse.class);
    }

    @Override
    public UpdatePaymentResponse update(int id, UpdatePaymentRequest updatePaymentRequest) {
        businesRules.checkIfPaymentExists(id);
        Payment pa = mapper.map(updatePaymentRequest, Payment.class);
        pa.setId(id);
        return mapper.map(pa, UpdatePaymentResponse.class);
    }

    @Override
    public GetPaymentResponse getById(int id) {
        businesRules.checkIfPaymentExists(id);
        Payment payment = repository.findById(id).orElseThrow();
        return mapper.map(payment, GetPaymentResponse.class);
    }

    @Override
    public List<GetAllPaymentsResponse> getAll() {
        return repository.findAll()
                .stream().map(payment -> mapper.map(payment, GetAllPaymentsResponse.class)).toList();
    }

    @Override
    public void delete(int id) {
        businesRules.checkIfPaymentExists(id);
        repository.deleteById(id);
    }
    @Override
    public void proccesSalePayment(CreateSalePaymentRequest createSalePaymentRequest) {
        businesRules.checkIfPaymentIsValid(createSalePaymentRequest);
        Payment payment = repository.findByCardNumber(createSalePaymentRequest.getCardNumber());
        businesRules.checkIfBalanceIdEnough(payment.getBalance(), createSalePaymentRequest.getTotalPrice());
        posService.pay();
        payment.setBalance(payment.getBalance() - createSalePaymentRequest.getTotalPrice());
        repository.save(payment);
    }
}