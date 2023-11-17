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
        @Digits(integer = 1, fraction = 0, message = "user id must be a number with a maximum of 1 digit")
        private Long userId;

        @NotNull(message = "addressId cannot be null")
        @Digits(integer = 1, fraction = 0, message = "address id must be a number with a maximum of 1 digit")
        private Integer addressId;
}
