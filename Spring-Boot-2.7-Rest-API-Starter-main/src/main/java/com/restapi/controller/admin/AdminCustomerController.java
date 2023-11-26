package com.restapi.controller.admin;

import com.restapi.model.Customer;
import com.restapi.response.AgentPropertyResponse;
import com.restapi.response.BookingResponse;
import com.restapi.response.CustomerResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AddressService;
import com.restapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/customer")
public class AdminCustomerController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllCustomers(){
        List<CustomerResponse> customerResponses=customerService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(customerResponses);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/bookings/all")
    public ResponseEntity<APIResponse> getAgentPropertyDetails(){
        List<BookingResponse> customerBookings=customerService.findCustomerBookings();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(customerBookings);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
