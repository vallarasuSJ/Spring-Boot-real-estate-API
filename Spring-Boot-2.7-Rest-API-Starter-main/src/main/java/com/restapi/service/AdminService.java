package com.restapi.service;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Property;
import com.restapi.repository.PropertyRepository;
import com.restapi.response.PropertyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    PropertyRepository propertyRepository;

    public PropertyResponse allowProperty(Integer propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("propertyId", "propertyId", propertyId));
        property.setApproved(true);

        System.out.println(property.getPropertyName());
        propertyRepository.save(property);
        return null;
    }
}
