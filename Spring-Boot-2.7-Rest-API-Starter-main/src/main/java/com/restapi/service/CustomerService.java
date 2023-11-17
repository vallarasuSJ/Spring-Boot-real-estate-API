package com.restapi.service;

import com.restapi.dto.CustomerDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Address;
import com.restapi.model.Agent;
import com.restapi.model.AppUser;
import com.restapi.model.Customer;
import com.restapi.repository.AddressRepository;
import com.restapi.repository.CustomerRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerDto customerDto;

    public  List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(CustomerRequest customerRequest) {
        Customer customer=customerDto.mapToCustomer(customerRequest);
        AppUser appUser=userRepository.findById(customerRequest.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("userId","userId", customerRequest.getUserId()));
        customer.setAppUser(appUser);
        Address address=addressRepository.findById(customerRequest.getAddressId())
                .orElseThrow(()->new ResourceNotFoundException("addressId","addressId", customerRequest.getAddressId()));
        customer.setAddress(address);
        customerRepository.save(customer);
        return customer;
    }
}
