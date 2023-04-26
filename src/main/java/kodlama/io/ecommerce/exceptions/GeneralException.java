package kodlama.io.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralException {
    @ExceptionHandler(DescriptionException.class)
    public ResponseEntity<?> handle(DescriptionException descriptionException){
        return new ResponseEntity<>(descriptionException.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PriceException.class)
    public ResponseEntity<?> handle(PriceException priceException){
        return new ResponseEntity<>(priceException.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(QuantityException.class)
    public ResponseEntity<?>handle(QuantityException quantityException){
        return new ResponseEntity<>(quantityException.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BrandExistsException.class)
    public ResponseEntity<?> handle(BrandExistsException brandExistsException){
        return new ResponseEntity<>(brandExistsException.getMessage(),HttpStatus.BAD_REQUEST);
    }
}