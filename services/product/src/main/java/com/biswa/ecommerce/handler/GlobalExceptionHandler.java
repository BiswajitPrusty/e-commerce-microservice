package com.biswa.ecommerce.handler;

import com.biswa.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ProductPurchaseException.class, EntityNotFoundException.class})
    public ResponseEntity<String> handle(ProductPurchaseException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exp) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors().forEach(
                error -> {
                    var errorField = (FieldError) error;
                    errors.put(errorField.getField(), error.getDefaultMessage());
                }
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(errors));
    }


}
