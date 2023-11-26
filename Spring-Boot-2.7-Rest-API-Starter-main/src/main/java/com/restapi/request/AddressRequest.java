package com.restapi.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressRequest {
    private Long id;

    @NotEmpty
    @Size(min = 3, message = "address should have at least 3 characters")
    private String address;

    @NotEmpty
    @Size(min = 3, message = "city should have at least 3 characters")
    private String city;

    @NotNull(message = "zipcode cannot be null")
    @Digits(integer = 5, fraction = 0, message = "Zip code must be a number with a maximum of 5 digits")
    private Long zipcode;


}
