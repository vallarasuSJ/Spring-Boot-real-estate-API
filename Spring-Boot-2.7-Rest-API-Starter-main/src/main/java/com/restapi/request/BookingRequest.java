package com.restapi.request;

import lombok.*;
import org.hibernate.Internal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingRequest {

    @NotNull(message = "userId cannot be null")
    @Digits(integer = 1, fraction = 0, message = "user id must be a number with a maximum of 1 digit")
    private Long userId;

    @NotNull(message = "propertyId cannot be null")
    @Digits(integer = 1, fraction = 0, message = "property id must be a number with a maximum of 1 digit")
    private Integer propertyId;
}
