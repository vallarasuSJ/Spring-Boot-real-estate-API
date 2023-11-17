package com.restapi.dto;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.restapi.model.Property;
import com.restapi.request.PropertyRequest;
import com.restapi.response.PropertyResponse;
import org.springframework.stereotype.Component;

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

    public PropertyResponse mapToPropertyResponse(List<Property> propertyList) {
        return new PropertyResponse(propertyList);
    }

}
