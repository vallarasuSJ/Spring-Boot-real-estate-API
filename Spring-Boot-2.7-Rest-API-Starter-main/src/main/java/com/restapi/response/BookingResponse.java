package com.restapi.response;

import com.restapi.model.Booked;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BookingResponse {
    private List<Booked> bookedList;
}
