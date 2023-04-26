package kodlama.io.ecommerce.common.constants;

public class Messages {
    public static class Sale{
        public static final String NotExists="SALE_NOT_EXISTS";
        public static final String NotForSale="PRODUCT_NOT_FOR_SALE";
        public static final String YinsufficientBalance="YOUR_BALANCE_IS_NOT_ENOUGH";
    }
    public static class Payment {
        public static final String NotFound = "PAYMENT_NOT_FOUND";
        public static final String CardNumberAlreadyExists = "CARD_NUMBER_ALREADY_EXISTS";
        public static final String NotEnoughMoney = "NOT_ENOUGH_MONEY";
        public static final String NotAValidPayment = "NOT_A_VALID_PAYMENT";
        public static final String Failed = "PAYMENT_FAILED";
    }
    public static class Invoice{
        public static final String NotFound = "INVOICE_NOT_FOUND";
    }
}