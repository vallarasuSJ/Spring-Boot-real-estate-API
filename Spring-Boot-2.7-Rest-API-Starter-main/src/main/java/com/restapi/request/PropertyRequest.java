package com.restapi.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PropertyRequest {

    private Integer id;

    private boolean isApproved;

//    @NotNull(message = "photo data cannot be null")
//    @Size(min = 1, max = 10 * 1024 * 1024, message = "Photo size must be between 1 byte and 10MB")
    private byte[] photo;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private Double price;

    @NotEmpty
    @Size(min = 2, message = "propertyName should have at least 2 characters")
    private String  propertyName;

    @NotNull(message = "agentId cannot be null")
    @Digits(integer = 1, fraction = 0, message = "agent id must be a number with a maximum of 1 digit")
    private Integer agentId;

    @NotNull(message = "addressId cannot be null")
    @Digits(integer = 1, fraction = 0, message = "address id must be a number with a maximum of 1 digit")
    private Integer addressId;
}
