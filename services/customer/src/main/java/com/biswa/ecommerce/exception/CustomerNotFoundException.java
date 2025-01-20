package com.biswa.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerNotFoundException extends RuntimeException{
    private String msg;

    public CustomerNotFoundException(String msg) {
        super(msg);
    }
}
