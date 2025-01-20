package com.biswa.ecommerce.customer;

import com.biswa.ecommerce.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final ModelMapper modelMapper;

    public String createCustomer(@Valid CustomerRequest request) {
        Customer customer = repository.save(modelMapper.map(request, Customer.class));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest request) {
        var customer = repository.findById(request.getId()).orElseThrow(() -> new CustomerNotFoundException(String.format("Cannot update customer::" +
                " No customer found with the customer ID:: %s", request.getId())));
        repository.save(modelMapper.map(request, Customer.class));
    }

    public List<CustomerResponse> findAllCustomers() {
        List<CustomerResponse> customers = repository.findAll().stream().map(customer ->
                modelMapper.map(customer, CustomerResponse.class)).collect(Collectors.toList());
        return customers;
    }

    public Boolean checkById(String customerId) {
        return repository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
       return repository.findById(customerId).map(customer ->
               modelMapper.map(customer, CustomerResponse.class))
               .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the customer ID:: %s", customerId)));
    }

    public void deleteById(String customerId) {
        var customer = repository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(String.format("Cannot delete customer::" +
                " No customer found with the customer ID:: %s", customerId)));
    }
}
