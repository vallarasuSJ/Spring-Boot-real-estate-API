package com.restapi.service;

import com.restapi.dto.PropertyDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Address;
import com.restapi.model.Agent;
import com.restapi.model.AppUser;
import com.restapi.model.Property;
import com.restapi.repository.AddressRepository;
import com.restapi.repository.AgentRepository;
import com.restapi.repository.PropertyRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.PropertyRequest;
import com.restapi.response.PropertyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyDto propertyDto;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private AddressRepository addressRepository;

    public PropertyResponse findAll() {
        List<Property> propertyList = propertyRepository.findAll();
        return propertyDto.mapToPropertyResponse(propertyList);
    }


    @Transactional
    public PropertyResponse create(PropertyRequest propertyRequest) {
        Property property = propertyDto.mapToProperty(propertyRequest);
        Agent agent = agentRepository.findById(propertyRequest.getAgentId())
                .orElseThrow(() -> new ResourceNotFoundException("agentId", "agentId", propertyRequest.getAgentId()));
        property.setAgent(agent);
        Address address = addressRepository.findById(propertyRequest.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("addressId", "addressId", propertyRequest.getAddressId()));
        property.setAddress(address);
        propertyRepository.save(property);
        return findAll();
    }


    public PropertyResponse update(PropertyRequest propertyRequest) {
        Property property = propertyRepository.findById(propertyRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("id", "id", propertyRequest.getId()));
        property.setPropertyName(propertyRequest.getPropertyName());
        property.setPrice(propertyRequest.getPrice());
        property.setPhoto(propertyRequest.getPhoto());

//      Property property=propertyDto.mapToProperty(propertyRequest);
        Agent agent = agentRepository.findById(propertyRequest.getAgentId())
                .orElseThrow(() -> new ResourceNotFoundException("agentId", "agentId", propertyRequest.getAgentId()));
        property.setAgent(agent);

        Address address = addressRepository.findById(propertyRequest.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("addressId", "addressId", propertyRequest.getAddressId()));
        property.setAddress(address);

        propertyRepository.save(property);
        return findAll();
    }

    public PropertyResponse deleteProperty(Integer id) {
        propertyRepository.deleteById(id);
        return findAll();
    }
}
