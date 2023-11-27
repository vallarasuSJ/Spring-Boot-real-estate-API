package com.restapi.service;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Property;
import com.restapi.repository.PropertyRepository;
import com.restapi.request.PropertyRequest;
import com.restapi.response.PropertyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    PropertyRepository propertyRepository;

    public Property allowProperty(PropertyRequest propertyRequest) {
        Property property = propertyRepository.findById(propertyRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("propertyId", "propertyId", propertyRequest.getId()));
        property.setApproved(true);

        propertyRepository.save(property);
        return property;
    }
}
