package com.restapi.response;

import com.restapi.model.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PropertyResponse {
    private Long id;
    private String propertyName;
    private Double price;
    private String photo;
    private String address;
    private String city;
    private Long zipcode;
    private Long contactNumber;
    private Long addressId;
    private Long categoryId;
    private boolean isApprove;
    private String agentName;
    private String categoryName;
}
