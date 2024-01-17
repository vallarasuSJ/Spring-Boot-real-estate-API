package com.restapi.dto;

import com.restapi.model.Booked;
import com.restapi.request.BookingRequest;
import com.restapi.response.BookingResponse;
import com.restapi.service.BookingService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingDto {
    public List<BookingResponse> mapToBookingResponse(List<Booked> bookedList) {
        List<BookingResponse> bookingResponses=new ArrayList<>();
        for(Booked booked:bookedList){
                BookingResponse bookingResponse = new BookingResponse();
                bookingResponse.setBookedId(booked.getId());
                bookingResponse.setPropertyId(booked.getProperty().getId());
                bookingResponse.setName(booked.getProperty().getAgent().getAgentName());
                bookingResponse.setPropertyName(booked.getProperty().getPropertyName());
                bookingResponse.setPrice(booked.getProperty().getPrice());
                bookingResponse.setAddress(booked.getProperty().getAddress().getAddress());
                bookingResponse.setBookedTime(booked.getBookedTime());
                bookingResponse.setPhoto(booked.getProperty().getPhoto());
                bookingResponse.setCity(booked.getProperty().getAddress().getCity());
                bookingResponse.setContactNumber(booked.getProperty().getAgent().getContactNumber());
                bookingResponse.setCustomerId(booked.getAppUser().getId());
                bookingResponses.add(bookingResponse);
        }
        return bookingResponses;
    }

    public List<BookingResponse> mapToUserBookingResponse(Long userId, List<Booked> bookedList) {
        List<BookingResponse> bookingResponses=new ArrayList<>();
        for(Booked booked:bookedList){
            if(userId==booked.getAppUser().getId()) {
                BookingResponse bookingResponse = new BookingResponse();
                bookingResponse.setBookedId(booked.getId());
                bookingResponse.setPropertyId(booked.getProperty().getId());
                bookingResponse.setName(booked.getProperty().getAgent().getAgentName());
                bookingResponse.setPropertyName(booked.getProperty().getPropertyName());
                bookingResponse.setPrice(booked.getProperty().getPrice());
                bookingResponse.setAddress(booked.getProperty().getAddress().getAddress());
                bookingResponse.setBookedTime(booked.getBookedTime());
                bookingResponse.setPhoto(booked.getProperty().getPhoto());
                bookingResponse.setCity(booked.getProperty().getAddress().getCity());
                bookingResponse.setContactNumber(booked.getProperty().getAgent().getContactNumber());
                bookingResponse.setCustomerId(booked.getAppUser().getId());
                bookingResponses.add(bookingResponse);
            }
        }
        return bookingResponses;
    }
}
