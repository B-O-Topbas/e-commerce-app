package kodlama.io.ecommerce.business.rules;

import kodlama.io.ecommerce.common.constants.Messages;
import kodlama.io.ecommerce.common.dto.CreateSalePaymentRequest;
import kodlama.io.ecommerce.core.exceptions.BusinessException;
import kodlama.io.ecommerce.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentBusinesRules {
private final PaymentRepository paymentRepository;
    public PaymentBusinesRules(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    public void checkIfPaymentExists(int id){
        if(!paymentRepository.existsById(id)){
            throw new BusinessException(Messages.Payment.NotFound);
        }
    }

    public void checkIfBalanceIdEnough(double balance, double price) {
        if (balance < price) {
            throw new BusinessException(Messages.Payment.NotEnoughMoney);
        }
    }

    public void checkIfCardExists(String cardNumber) {
        if (paymentRepository.existsByCardNumber(cardNumber)) {
            throw new BusinessException(Messages.Payment.CardNumberAlreadyExists);
        }
    }

    public void checkIfPaymentIsValid(CreateSalePaymentRequest request) {
        if (!paymentRepository.existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
                request.getCardNumber(),
                request.getCardHolder(),
                request.getCardExpirationYear(),
                request.getCardExpirationMonth(),
                request.getCardCvv()
        )) {
            throw new BusinessException(Messages.Payment.NotAValidPayment);
        }
    }

}