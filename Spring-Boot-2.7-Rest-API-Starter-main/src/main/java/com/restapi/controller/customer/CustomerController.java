package com.restapi.controller.customer;

import com.restapi.model.Agent;
import com.restapi.model.Customer;
import com.restapi.model.Role;
import com.restapi.request.*;
import com.restapi.response.AddressResponse;
import com.restapi.response.AgentResponse;
import com.restapi.response.BookingResponse;
import com.restapi.response.CustomerResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AddressService;
import com.restapi.service.BookingService;
import com.restapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RolesAllowed(Role.USER)
public class CustomerController {
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

    @PostMapping
    public ResponseEntity<APIResponse> CreateCustomer(@Valid @RequestBody CustomerRequest customerRequest){
        List<CustomerResponse> customerResponses=customerService.createCustomer(customerRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(customerResponses);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteCustomer(@PathVariable Long id) {
        List<CustomerResponse> customerResponses = customerService.deleteCustomer(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(customerResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/address")
    public ResponseEntity<APIResponse> createAddress(@Valid @RequestBody PropertyRequest propertyRequest){
        AddressResponse addressResponse=addressService.create(propertyRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(addressResponse);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }


}
