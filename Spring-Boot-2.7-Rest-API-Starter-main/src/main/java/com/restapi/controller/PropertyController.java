package com.restapi.controller;

import com.restapi.model.Property;
import com.restapi.repository.PropertyRepository;
import com.restapi.response.PropertyResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllProperties(){
        List<Property> propertyList=propertyService.findAll().getPropertyList();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(propertyList);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }
}
