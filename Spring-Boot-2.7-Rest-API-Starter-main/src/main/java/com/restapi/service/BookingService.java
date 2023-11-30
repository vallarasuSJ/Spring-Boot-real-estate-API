package com.restapi.service;

import com.restapi.dto.BookingDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Address;
import com.restapi.model.AppUser;
import com.restapi.model.Booked;
import com.restapi.model.Property;
import com.restapi.repository.BookedRepository;
import com.restapi.repository.PropertyRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.BookingRequest;
import com.restapi.response.AddressResponse;
import com.restapi.response.BookingResponse;
import com.restapi.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingDto bookingDto;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private BookedRepository bookedRepository;

    public List<BookingResponse> findAll() {
        List<Booked> bookedList = bookedRepository.findAll();
        List<BookingResponse> bookingResponses=bookingDto.mapToBookingResponse(bookedList);
        return bookingResponses;
    }

    public List<BookingResponse> createBooking(BookingRequest bookingRequest) {
        Booked booked=new Booked();
        AppUser appUser=userRepository.findById(bookingRequest.getCustomerId())
                .orElseThrow(()->new ResourceNotFoundException("userId","userId",bookingRequest.getCustomerId()));
        booked.setAppUser(appUser);
        Property property=propertyRepository.findById(bookingRequest.getId())
                .orElseThrow(()->new ResourceNotFoundException("Id","propertyId",bookingRequest.getId()));
        booked.setProperty(property);
        bookedRepository.save(booked);
        return findAll();
    }


    public List<BookingResponse> cancelBooking(Long id) {
        bookedRepository.deleteById(id);
        return findAll();
    }

    public List<BookingResponse> findUserBookings(Long userId) {
        List<Booked> bookedList = bookedRepository.findAll();
        List<BookingResponse> bookingResponses=bookingDto.mapToUserBookingResponse(userId,bookedList);
        return bookingResponses;

    }
}
