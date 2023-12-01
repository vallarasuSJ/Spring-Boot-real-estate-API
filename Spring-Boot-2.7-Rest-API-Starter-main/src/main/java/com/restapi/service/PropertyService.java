package com.restapi.service;

import com.restapi.dto.AddressDto;
import com.restapi.dto.PropertyDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.request.PropertyRequest;
import com.restapi.response.PropertyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
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

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StorageService storageService;



    public List<PropertyResponse> findAll() {
        List<Property> propertyList = propertyRepository.findAll();
        List<PropertyResponse> propertyResponses = propertyDto.mapToPropertyResponse(propertyList);
        return propertyResponses;
    }


    @Transactional
    public List<PropertyResponse> create(PropertyRequest propertyRequest,Long categoryId,Long agentId) {
        Address address = AddressDto.mapToAddress(propertyRequest);
        address = addressRepository.save(address);
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("categoryId","categoryId",categoryId));
        Property property = propertyDto.mapToProperty(propertyRequest);
        Agent agent=agentRepository.findByUserId(agentId)
                .orElseThrow(()->new ResourceNotFoundException("agentId","agentId",agentId));
        property.setAgent(agent);
        property.setAddress(address);
        property.setCategory(category);
        propertyRepository.save(property);
        return findAll();
    }


    public List<PropertyResponse> update(PropertyRequest propertyRequest, Long id) {
        Property property=propertyRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("id","id",id));
        property.setPropertyName(propertyRequest.getPropertyName());
        property.setPrice(propertyRequest.getPrice());
        property.setPhoto(property.getPhoto());
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

    public File getFile(Long id) throws IOException {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "id", id));
        Resource resource = storageService.loadFileAsResource(property.getPhoto());
        return resource.getFile();
    }

    public List<PropertyResponse> findAgentProperties(Long agentId) {
        List<Property> propertyList = propertyRepository.findAll();
        List<PropertyResponse> propertyResponses = propertyDto.mapToAgentPropertyResponse(agentId,propertyList);
        return propertyResponses;
    }
}
