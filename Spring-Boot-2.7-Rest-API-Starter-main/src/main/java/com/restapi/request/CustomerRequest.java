package com.restapi.request;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerRequest {

    private Long id;

    @NotEmpty
    @Size(min = 2, message = "customerName should have at least 5 characters")
    private String CustomerName;

    @NotNull(message = "userId cannot be null")
    private Long userId;

    @NotNull(message = "Contact number cannot be null")
    @Digits(integer = 10, fraction = 0, message = "Contact number must be a number with a maximum of 10 digits")
    private Long contactNumber;

}
