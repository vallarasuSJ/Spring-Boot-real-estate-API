package com.restapi.service;

import com.restapi.dto.AddressDto;
import com.restapi.dto.PropertyDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Address;
import com.restapi.model.Agent;
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
import java.util.Optional;

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

    public List<PropertyResponse> findAll() {
        List<Property> propertyList = propertyRepository.findAll();
        List<PropertyResponse> propertyResponses = propertyDto.mapToPropertyResponse(propertyList);
        return propertyResponses;
    }


    @Transactional
    public List<PropertyResponse> create(PropertyRequest propertyRequest) {

        Address address = AddressDto.mapToAddress(propertyRequest);
        address = addressRepository.save(address);
        Property property = propertyDto.mapToProperty(propertyRequest);
        Agent agent = agentRepository.findById(propertyRequest.getAgentId())
                .orElseThrow(() -> new ResourceNotFoundException("agentId", "agentId", propertyRequest.getAgentId()));
        property.setAgent(agent);
        property.setAddress(address);
        propertyRepository.save(property);
        return findAll();
    }


    public List<PropertyResponse> update(PropertyRequest propertyRequest) {
        Property property=propertyRepository.findById(propertyRequest.getId())
                .orElseThrow(()->new ResourceNotFoundException("id","id",propertyRequest.getId()));
        property.setPropertyName(propertyRequest.getPropertyName());
        property.setPrice(propertyRequest.getPrice());
        Address address = AddressDto.mapToAddress(propertyRequest);
        address = addressRepository.save(address);
        Agent agent = agentRepository.findById(propertyRequest.getAgentId())
                .orElseThrow(() -> new ResourceNotFoundException("agentId", "agentId", propertyRequest.getAgentId()));
        property.setAgent(agent);
        property.setAddress(address);
        propertyRepository.save(property);
        return findAll();
    }

    public List<PropertyResponse> deleteProperty(Long id) {
        propertyRepository.deleteById(id);
        return findAll();
    }

    public PropertyResponse getSelectedProperty(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "id", id));
        System.out.println(property);
        PropertyResponse propertyResponse=propertyDto.mapToPropertyDetail(property);
        return propertyResponse;
    }
}
