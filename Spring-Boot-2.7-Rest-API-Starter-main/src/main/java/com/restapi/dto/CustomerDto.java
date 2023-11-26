package com.restapi.dto;

import com.restapi.model.AppUser;
import com.restapi.model.Booked;
import com.restapi.model.Customer;
import com.restapi.request.CustomerRequest;
import com.restapi.request.RegisterRequest;
import com.restapi.response.BookingResponse;
import com.restapi.response.CustomerResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDto {
    public static Customer maptoCustomer(RegisterRequest registerRequest, AppUser appUser) {
        System.out.println("hello");
        Customer customer=new Customer();
        customer.setCustomerName(registerRequest.getName());
        customer.setContactNumber(registerRequest.getContact());
        customer.setAppUser(appUser);
        return customer;

    }

    public Customer mapToCustomer(CustomerRequest customerRequest) {
        Customer customer=new Customer();
        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setContactNumber(customerRequest.getContactNumber());
        return customer;

    }

    public List<CustomerResponse> mapToCustomerResponse(List<Customer> customerList) {
        List<CustomerResponse> customerResponses=new ArrayList<>();
        for(Customer customer:customerList){
            CustomerResponse customerResponse=new CustomerResponse();
            customerResponse.setCustomerName(customer.getCustomerName());
            customerResponse.setContactNumber(customer.getContactNumber());
            customerResponses.add(customerResponse);
        }
        return  customerResponses;
    }

    public List<BookingResponse> maptoCustomerBookings(List<Booked> bookedList) {
        List<BookingResponse> customerBookings=new ArrayList<>();
        for(Booked booked:bookedList){
            BookingResponse bookingResponse=new BookingResponse();
            bookingResponse.setName(booked.getAppUser().getCustomer().getCustomerName());
            bookingResponse.setPropertyName(booked.getProperty().getPropertyName());
            bookingResponse.setContactNumber(booked.getAppUser().getCustomer().getContactNumber());
            bookingResponse.setAddress(booked.getProperty().getAddress().getAddress());
            bookingResponse.setCity(booked.getProperty().getAddress().getCity());
            bookingResponse.setPrice(booked.getProperty().getPrice());
            bookingResponse.setZipcode(booked.getProperty().getAddress().getZipcode());
            bookingResponse.setBookedTime(booked.getBookedTime());
            customerBookings.add(bookingResponse);
        }
        return customerBookings;
    }
}
