package com.restapi.dto;

import com.restapi.model.Booked;
import com.restapi.response.BookingResponse;
import com.restapi.service.BookingService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingDto {
    public BookingResponse mapToBookingResponse(List<Booked> bookedList) {
        return new BookingResponse(bookedList);
    }
}
