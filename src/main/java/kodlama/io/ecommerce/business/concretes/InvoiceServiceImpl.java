package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.InvoiceService;
import kodlama.io.ecommerce.business.dto.request.create.invoice.CreateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.request.update.invoice.UpdateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.response.create.invoice.CreateInvoiceResponse;
import kodlama.io.ecommerce.business.dto.response.get.invoice.GetAllInvoicesResponse;
import kodlama.io.ecommerce.business.dto.response.get.invoice.GetInvoiceResponse;
import kodlama.io.ecommerce.business.dto.response.update.invoice.UpdateInvoiceResponse;
import kodlama.io.ecommerce.business.rules.InvoiceBussinessRules;
import kodlama.io.ecommerce.entities.Invoice;
import kodlama.io.ecommerce.repository.InvoiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ModelMapper modelMapper;
    private final InvoiceBussinessRules bussinessRules;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, ModelMapper modelMapper, InvoiceBussinessRules bussinessRules) {
        this.invoiceRepository = invoiceRepository;
        this.modelMapper = modelMapper;
        this.bussinessRules = bussinessRules;
    }
    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest) {
        Invoice invoice = modelMapper.map(createInvoiceRequest, Invoice.class);
        invoice.setId(0);
        invoice.setTotalPrice(getTotalPrice(invoice));
        invoiceRepository.save(invoice);
        return modelMapper.map(invoice, CreateInvoiceResponse.class);
    }

    @Override
    public UpdateInvoiceResponse update(int id, UpdateInvoiceRequest invoiceRequest) {
        bussinessRules.checkInvoiceExists(id);
        Invoice in = modelMapper.map(invoiceRequest, Invoice.class);
        in.setId(id);
        in.setTotalPrice(getTotalPrice(in));
        invoiceRepository.save(in);
        return modelMapper.map(in, UpdateInvoiceResponse.class);
    }
    @Override
    public GetInvoiceResponse getById(int id) {
        bussinessRules.checkInvoiceExists(id);
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        return modelMapper.map(invoice, GetInvoiceResponse.class);
    }
    @Override
    public List<GetAllInvoicesResponse> getAll() {
        return invoiceRepository.findAll()
                .stream()
                .map(invoice -> modelMapper.map(invoice, GetAllInvoicesResponse.class)).toList();
    }
    @Override
    public void delete(int id) {
        bussinessRules.checkInvoiceExists(id);
        invoiceRepository.deleteById(id);
    }
    private double getTotalPrice(Invoice invoice) {
        return invoice.getUnitPrice() * invoice.getNumberOfPurchases();
    }
}