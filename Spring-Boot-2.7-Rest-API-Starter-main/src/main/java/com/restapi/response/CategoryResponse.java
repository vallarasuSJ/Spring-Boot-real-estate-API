package com.restapi.response;

import com.restapi.model.Property;
import com.restapi.request.CategoryRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class CategoryResponse {

    private Long categoryId;
    private String categoryName;
    private Long propertyId;
    private String propertyName;
    private Double price;
    private String photo;
    private String address;
    private String city;
    private Long zipcode;
    private boolean isApprove;
    private Long contactNumber;
    private String agentName;

}
