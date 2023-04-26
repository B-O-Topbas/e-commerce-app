package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.InvoiceService;
import kodlama.io.ecommerce.business.abstracts.PaymentService;
import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.business.abstracts.SaleService;
import kodlama.io.ecommerce.business.dto.request.create.invoice.CreateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.request.create.sale.CreateSaleRequest;
import kodlama.io.ecommerce.business.rules.SaleBusinessRules;
import kodlama.io.ecommerce.common.dto.CreateSalePaymentRequest;
import kodlama.io.ecommerce.business.dto.request.update.sale.UpdateSaleRequest;
import kodlama.io.ecommerce.business.dto.response.create.sale.CreateSaleResponse;
import kodlama.io.ecommerce.business.dto.response.get.product.GetProductResponse;
import kodlama.io.ecommerce.business.dto.response.get.sale.GetAllSalesResponse;
import kodlama.io.ecommerce.business.dto.response.get.sale.GetSaleResponse;
import kodlama.io.ecommerce.business.dto.response.update.sale.UpdateSaleResponse;
import kodlama.io.ecommerce.entities.Sale;
import kodlama.io.ecommerce.repository.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final ModelMapper mapper;
    private final ProductService productService;
    private final PaymentService paymentService;
    private final InvoiceService invoiceService;
    private final SaleBusinessRules saleBusinessRules;

    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper mapper, ProductService productService
            , PaymentService paymentService, InvoiceService invoiceService, SaleBusinessRules saleBusinessRules) {
        this.saleRepository = saleRepository;
        this.mapper = mapper;
        this.productService = productService;
        this.paymentService = paymentService;
        this.invoiceService = invoiceService;
        this.saleBusinessRules = saleBusinessRules;
    }

    @Override
    public CreateSaleResponse add(CreateSaleRequest createSaleRequest) {
        GetProductResponse product = productService.getProductById(createSaleRequest.getProductId());
        saleBusinessRules.checkVisibility(product.isSituation());
        saleBusinessRules.checkStockIsEnough(product.getStockQuantity(),createSaleRequest.getNumberOfPurchases());
        Sale sale = mapper.map(createSaleRequest, Sale.class);
        sale.setId(0);
        sale.setTotalPrice(getTotalPrice(sale));
        sale.setProductId(createSaleRequest.getProductId());
        sale.setDateOfPurchase(LocalDateTime.now());

        CreateSalePaymentRequest createSalePaymentRequest = new CreateSalePaymentRequest();
        mapper.map(createSaleRequest.getPaymentRequest(), createSalePaymentRequest);
        createSalePaymentRequest.setTotalPrice(getTotalPrice(sale));
        paymentService.proccesSalePayment(createSalePaymentRequest);
        saleRepository.save(sale);

        CreateInvoiceRequest invoiceRequest = new CreateInvoiceRequest();
        createInvoiceRequest(createSaleRequest, invoiceRequest, sale);
        invoiceService.add(invoiceRequest);
        return mapper.map(sale, CreateSaleResponse.class);
    }

    @Override
    public UpdateSaleResponse updateSale(int id, UpdateSaleRequest updateSaleRequest) {
        saleBusinessRules.checkExistById(id);
        Sale sale = mapper.map(updateSaleRequest, Sale.class);
        sale.setId(id);
        sale.setTotalPrice(getTotalPrice(sale));
        saleRepository.save(sale);
        return mapper.map(sale, UpdateSaleResponse.class);
    }

    @Override
    public GetSaleResponse getSaleById(int id) {
        saleBusinessRules.checkExistById(id);
        Sale sale = saleRepository.findById(id).orElseThrow();
        return mapper.map(sale, GetSaleResponse.class);
    }

    @Override
    public List<GetAllSalesResponse> getAll() {
        return saleRepository.findAll()
                .stream()
                .map(sale -> mapper.map(sale, GetAllSalesResponse.class)).toList();
    }

    @Override
    public void delete(int id) {
        saleBusinessRules.checkExistById(id);
        saleRepository.deleteById(id);
    }

    private double getTotalPrice(Sale sale) {
        return sale.getUnitPrice() * sale.getNumberOfPurchases();
    }

    private void createInvoiceRequest(CreateSaleRequest createSaleRequest
            , CreateInvoiceRequest invoiceRequest
            , Sale sale) {
        GetProductResponse getProductResponse = productService.getProductById(createSaleRequest.getProductId());
        invoiceRequest.setProductName(getProductResponse.getName());
        invoiceRequest.setUnitPrice(getProductResponse.getUnirPrice());
        invoiceRequest.setCardHolder(createSaleRequest.getPaymentRequest().getCardHolder());
        invoiceRequest.setNumberOfPurchases(createSaleRequest.getNumberOfPurchases());
        invoiceRequest.setDateOfPurchase(sale.getDateOfPurchase());
    }
}