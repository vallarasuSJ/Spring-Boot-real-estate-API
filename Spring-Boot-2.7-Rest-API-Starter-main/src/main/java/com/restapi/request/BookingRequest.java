package com.restapi.request;

import lombok.*;
import org.hibernate.Internal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingRequest {

    @NotNull(message = "userId cannot be null")
    private Long customerId;

    @NotNull(message = "propertyId cannot be null")
    private Long id;
}
