package com.restapi.dto;

import com.restapi.model.Customer;
import com.restapi.request.CustomerRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomerDto {
    public Customer mapToCustomer(CustomerRequest customerRequest) {
        Customer customer=new Customer();
        customer.setCustomerName(customerRequest.getCustomerName());
        return customer;

    }
}
