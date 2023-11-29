package com.restapi.response;

import com.restapi.model.Booked;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookingResponse {
    private Long propertyId;
    private String name;
    private String photo;
    private String propertyName;
    private String Address;
    private String city;
    private Long zipcode;
    private Long contactNumber;
    private Double price;
    private Long customerId;
    private LocalDateTime bookedTime;

}
