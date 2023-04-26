package kodlama.io.ecommerce.api.controller;

import kodlama.io.ecommerce.business.abstracts.PaymentService;
import kodlama.io.ecommerce.business.dto.request.create.payment.CreatePaymentRequest;
import kodlama.io.ecommerce.business.dto.request.update.payment.UpdatePaymentRequest;
import kodlama.io.ecommerce.business.dto.response.create.payment.CreatePaymentResponse;
import kodlama.io.ecommerce.business.dto.response.get.payment.GetAllPaymentsResponse;
import kodlama.io.ecommerce.business.dto.response.get.payment.GetPaymentResponse;
import kodlama.io.ecommerce.business.dto.response.update.payment.UpdatePaymentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {
    private final PaymentService paymentService;
    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @GetMapping
    public ResponseEntity<List<GetAllPaymentsResponse>> getAll(){
        return new ResponseEntity<>(paymentService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetPaymentResponse> getPaymentById(@PathVariable int id){
        return new ResponseEntity<>(paymentService.getById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CreatePaymentResponse> create(@RequestBody CreatePaymentRequest createPaymentRequest){
        return new ResponseEntity<>(paymentService.add(createPaymentRequest),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdatePaymentResponse> update(@PathVariable int id
            , @RequestBody UpdatePaymentRequest updatePaymentRequest){
        return new ResponseEntity<>(paymentService.update(id,updatePaymentRequest),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        paymentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}