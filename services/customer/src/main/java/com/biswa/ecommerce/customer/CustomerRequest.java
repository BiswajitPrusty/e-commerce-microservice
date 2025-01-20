package com.biswa.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

    private String id;
    @NotNull(message = "Customer firstname is required")
    private String firstname;
    @NotNull(message = "Customer lastname is required")
    private String lastname;
    @NotNull(message = "Customer email is required")
    @Email(message = "Customer email is not a valid email address")
    private String email;
    private Address address;
}
