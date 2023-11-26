package com.restapi.controller.customer;

import com.restapi.request.BookingRequest;
import com.restapi.response.BookingResponse;
import com.restapi.response.CustomerResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerBookingController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<APIResponse> bookingProperty(@RequestBody BookingRequest bookingRequest){
        List<BookingResponse> bookingResponses=bookingService.createBooking(bookingRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(bookingResponses);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @GetMapping("/booking/all")
    public  ResponseEntity<APIResponse> getAllBookedProperties(){
        List<BookingResponse> bookingResponses=bookingService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(bookingResponses);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }
    @DeleteMapping("/booking/cancel/{id}")
    public ResponseEntity<APIResponse> cancelBooking(@PathVariable Long id) {
        List<BookingResponse> bookingResponses = bookingService.cancelBooking(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(bookingResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
