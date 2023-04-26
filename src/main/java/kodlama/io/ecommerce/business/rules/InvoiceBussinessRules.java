package kodlama.io.ecommerce.business.rules;

import kodlama.io.ecommerce.common.constants.Messages;
import kodlama.io.ecommerce.core.exceptions.BusinessException;
import kodlama.io.ecommerce.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class InvoiceBussinessRules {
    private final InvoiceRepository invoiceRepository;
    public InvoiceBussinessRules(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
    public void checkInvoiceExists(int id){
        if (!invoiceRepository.existsById(id)){
            throw new BusinessException(Messages.Invoice.NotFound);
        }
    }
}