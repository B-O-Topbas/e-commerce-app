package kodlama.io.ecommerce.business.rules;

import kodlama.io.ecommerce.common.constants.Messages;
import kodlama.io.ecommerce.core.exceptions.BusinessException;
import kodlama.io.ecommerce.repository.SaleRepository;
import org.springframework.stereotype.Service;

@Service
public class SaleBusinessRules {
    private final SaleRepository saleRepository;

    public SaleBusinessRules(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public void checkExistById(int id){
        if (!saleRepository.existsById(id)){
            throw new BusinessException(Messages.Sale.NotExists);
        }
    }

    public void  checkVisibility(boolean value){
        if (!value){
            throw new BusinessException(Messages.Sale.NotForSale);
        }
    }
    public void checkStockIsEnough(int stock,int wanted){
        if (stock<=0||stock<wanted){
            throw new BusinessException(Messages.Sale.YinsufficientBalance);
        }
    }
}