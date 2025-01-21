package com.biswa.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
