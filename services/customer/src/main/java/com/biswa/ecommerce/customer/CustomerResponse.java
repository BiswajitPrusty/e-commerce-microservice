package com.biswa.ecommerce.customer;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;
}
