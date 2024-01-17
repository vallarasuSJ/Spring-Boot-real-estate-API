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

    //Endpoint to create a booking for a property
    @PostMapping("/booking")
    public ResponseEntity<APIResponse> bookingProperty(@RequestBody BookingRequest bookingRequest) {
       bookingService.createBooking(bookingRequest);
        apiResponse.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //endpoint to get all booked property by the specific user
    @GetMapping("/booking/{userId}")
    public ResponseEntity<APIResponse> getAllBookedProperties(@PathVariable Long userId) {
        List<BookingResponse> bookingResponses = bookingService.findUserBookings(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(bookingResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //endpoint to cancel booking
    @DeleteMapping("/booking/cancel/{id}")
    public ResponseEntity<APIResponse> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
