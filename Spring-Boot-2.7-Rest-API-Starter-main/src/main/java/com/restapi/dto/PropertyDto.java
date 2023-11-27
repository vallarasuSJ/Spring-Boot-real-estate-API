package com.restapi.dto;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.restapi.model.Property;
import com.restapi.request.PropertyRequest;
import com.restapi.response.PropertyResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PropertyDto {

    public Property mapToProperty(PropertyRequest propertyRequest) {
        Property property=new Property();
        property.setPropertyName(propertyRequest.getPropertyName());
        property.setPhoto(propertyRequest.getPhoto());
        property.setPrice(propertyRequest.getPrice());
        return property;
    }

    public List<PropertyResponse> mapToPropertyResponse(List<Property> propertyList) {
       List<PropertyResponse>propertyResponses=new ArrayList<>();
       for(Property property:propertyList){
           PropertyResponse propertyResponse=new PropertyResponse();
           propertyResponse.setId(property.getId());
           propertyResponse.setPropertyName(property.getPropertyName());
           propertyResponse.setAddress(property.getAddress().getAddress());
           propertyResponse.setCity(property.getAddress().getCity());
           propertyResponse.setZipcode(property.getAddress().getZipcode());
           propertyResponse.setPhoto(property.getPhoto());
           propertyResponse.setPrice(property.getPrice());
           propertyResponse.setApprove(property.isApproved());
           propertyResponses.add(propertyResponse);
       }
       return propertyResponses;
    }

    public PropertyResponse mapToPropertyDetail(Property property) {
        PropertyResponse propertyResponse=new PropertyResponse();
        propertyResponse.setId(property.getId());
        propertyResponse.setPropertyName(property.getPropertyName());
        propertyResponse.setPrice(property.getPrice());
        propertyResponse.setAddressId(property.getAddress().getId());
        propertyResponse.setAddress(property.getAddress().getAddress());
        propertyResponse.setCity(property.getAddress().getCity());
        propertyResponse.setZipcode(property.getAddress().getZipcode());
        propertyResponse.setPhoto(property.getPhoto());
        return propertyResponse;
    }

}
