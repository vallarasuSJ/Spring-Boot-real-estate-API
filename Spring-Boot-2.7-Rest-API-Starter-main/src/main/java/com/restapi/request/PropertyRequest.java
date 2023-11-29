package com.restapi.request;

import com.restapi.model.Agent;
import com.restapi.model.AppUser;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PropertyRequest {

    private Long id;

    private boolean isApproved;

//    @NotNull(message = "photo data cannot be null")
//    @Size(min = 1, max = 10 * 1024 * 1024, message = "Photo size must be between 1 byte and 10MB")
    private String photo;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private Double price;

    @NotEmpty
    @Size(min = 2, message = "propertyName should have at least 2 characters")
    private String  propertyName;

    @NotNull(message = "agentId cannot be null")
    private Long agentId;

    @NotEmpty
    @Size(min = 3, message = "address should have at least 3 characters")
    private String address;

    @NotEmpty
    @Size(min = 3, message = "city should have at least 3 characters")
    private String city;

    @NotNull(message = "zipcode cannot be null")
    @Digits(integer = 5, fraction = 0, message = "Zip code must be a number with a maximum of 5 digits")
    private Long zipcode;

    private Long categoryId;

    private Long addressId;

    private AppUser appUser;

    private Agent agent;
}
