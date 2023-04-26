package kodlama.io.ecommerce.api.controller;

import kodlama.io.ecommerce.business.abstracts.SaleService;
import kodlama.io.ecommerce.business.dto.request.create.sale.CreateSaleRequest;
import kodlama.io.ecommerce.business.dto.request.update.sale.UpdateSaleRequest;
import kodlama.io.ecommerce.business.dto.response.create.sale.CreateSaleResponse;
import kodlama.io.ecommerce.business.dto.response.get.sale.GetAllSalesResponse;
import kodlama.io.ecommerce.business.dto.response.get.sale.GetSaleResponse;
import kodlama.io.ecommerce.business.dto.response.update.sale.UpdateSaleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesController {
    private final SaleService saleService;
    public SalesController(SaleService saleService) {
        this.saleService = saleService;
    }
    @GetMapping
    public ResponseEntity<List<GetAllSalesResponse>> getAll(){
        return new ResponseEntity<>(saleService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetSaleResponse> getSaleById(@PathVariable int id){
        return new ResponseEntity<>(saleService.getSaleById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CreateSaleResponse> create(@RequestBody CreateSaleRequest createSaleRequest){
        return new ResponseEntity<>(saleService.add(createSaleRequest),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdateSaleResponse> update(@PathVariable int id
            , @RequestBody UpdateSaleRequest updateSaleRequest){
        return new ResponseEntity<>(saleService.updateSale(id,updateSaleRequest),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        saleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}