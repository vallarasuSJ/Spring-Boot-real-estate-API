package com.restapi.service;

import com.restapi.dto.CustomerDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.AddressRepository;
import com.restapi.repository.BookedRepository;
import com.restapi.repository.CustomerRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.CustomerRequest;
import com.restapi.response.AgentPropertyResponse;
import com.restapi.response.BookingResponse;
import com.restapi.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookedRepository bookedRepository;

    @Autowired
    private CustomerDto customerDto;

    public List<CustomerResponse> findAll() {
        List<Customer> customerList=customerRepository.findAll();
        List<CustomerResponse> customerResponses=customerDto.mapToCustomerResponse(customerList);
        return customerResponses;
    }

    public List<CustomerResponse> createCustomer(CustomerRequest customerRequest) {
        Optional<Customer> optionalCustomer=customerRepository.findById(customerRequest.getUserId());
        if(optionalCustomer.isEmpty()){
            Customer customer=customerDto.mapToCustomer(customerRequest);
            AppUser appUser=userRepository.findById(customerRequest.getUserId())
                    .orElseThrow(()->new ResourceNotFoundException("userId","userId", customerRequest.getUserId()));
            customer.setAppUser(appUser);
            customerRepository.save(customer);
            return findAll();
        }
        return null;
    }

    public List<CustomerResponse> deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        return findAll();
    }

    public List<BookingResponse> findCustomerBookings() {
        List<Booked> bookedList=bookedRepository.findAll();
        System.out.println(bookedList.get(0));
        List<BookingResponse> customerBookings=customerDto.maptoCustomerBookings(bookedList);
        return customerBookings;

    }
}
