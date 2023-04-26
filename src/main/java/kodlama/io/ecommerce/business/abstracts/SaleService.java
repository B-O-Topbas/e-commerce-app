package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.request.create.sale.CreateSaleRequest;
import kodlama.io.ecommerce.business.dto.request.update.sale.UpdateSaleRequest;
import kodlama.io.ecommerce.business.dto.response.create.sale.CreateSaleResponse;
import kodlama.io.ecommerce.business.dto.response.get.sale.GetAllSalesResponse;
import kodlama.io.ecommerce.business.dto.response.get.sale.GetSaleResponse;
import kodlama.io.ecommerce.business.dto.response.update.sale.UpdateSaleResponse;

import java.util.List;

public interface SaleService {
    CreateSaleResponse add(CreateSaleRequest createSaleRequest);
    UpdateSaleResponse updateSale(int id,UpdateSaleRequest updateSaleRequest);
    GetSaleResponse getSaleById(int id);
   List<GetAllSalesResponse> getAll();
    void delete(int id);
}
