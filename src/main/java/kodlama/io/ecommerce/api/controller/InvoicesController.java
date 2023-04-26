package kodlama.io.ecommerce.api.controller;

import kodlama.io.ecommerce.business.abstracts.InvoiceService;
import kodlama.io.ecommerce.business.dto.request.create.invoice.CreateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.request.update.invoice.UpdateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.response.create.invoice.CreateInvoiceResponse;
import kodlama.io.ecommerce.business.dto.response.get.invoice.GetAllInvoicesResponse;
import kodlama.io.ecommerce.business.dto.response.get.invoice.GetInvoiceResponse;
import kodlama.io.ecommerce.business.dto.response.update.invoice.UpdateInvoiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {
    private final InvoiceService invoiceService;

    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    @GetMapping
    public ResponseEntity<List<GetAllInvoicesResponse>> getAll(){
        return new ResponseEntity<>(invoiceService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetInvoiceResponse> getInvoiceById(@PathVariable int id){
        return new ResponseEntity<>(invoiceService.getById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CreateInvoiceResponse> create(@RequestBody CreateInvoiceRequest createInvoiceRequest){
        return new ResponseEntity<>(invoiceService.add(createInvoiceRequest),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdateInvoiceResponse> update(@PathVariable int id
            , @RequestBody UpdateInvoiceRequest invoiceRequest){
        return new ResponseEntity<>(invoiceService.update(id,invoiceRequest),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        invoiceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}