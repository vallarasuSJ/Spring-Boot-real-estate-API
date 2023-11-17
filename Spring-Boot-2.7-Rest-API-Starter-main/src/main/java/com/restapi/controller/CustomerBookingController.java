package com.restapi.controller;

import com.restapi.request.BookingRequest;
import com.restapi.response.BookingResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerBookingController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<APIResponse> bookingProperty(@Valid  @RequestBody BookingRequest bookingRequest){
        BookingResponse bookingResponse=bookingService.createBooking(bookingRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(bookingResponse);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
