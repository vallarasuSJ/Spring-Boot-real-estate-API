package com.restapi.response;

import com.restapi.model.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PropertyResponse {
    private List<Property> propertyList;
}
