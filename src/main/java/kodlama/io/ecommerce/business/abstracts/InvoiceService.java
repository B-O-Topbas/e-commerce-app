package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.request.create.invoice.CreateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.request.update.invoice.UpdateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.response.create.invoice.CreateInvoiceResponse;
import kodlama.io.ecommerce.business.dto.response.get.invoice.GetAllInvoicesResponse;
import kodlama.io.ecommerce.business.dto.response.get.invoice.GetInvoiceResponse;
import kodlama.io.ecommerce.business.dto.response.update.invoice.UpdateInvoiceResponse;

import java.util.List;

public interface InvoiceService {
    CreateInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest);
    UpdateInvoiceResponse update(int id,UpdateInvoiceRequest invoiceRequest);
    GetInvoiceResponse getById(int id);
    List<GetAllInvoicesResponse> getAll();
    void delete(int id);
}