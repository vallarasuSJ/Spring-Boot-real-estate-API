package com.restapi.controller.Agent;

import com.restapi.model.Property;
import com.restapi.model.Role;
import com.restapi.request.PropertyRequest;
import com.restapi.response.PropertyResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agent")
@RolesAllowed(Role.AGENT)
public class AgentPropertyController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private PropertyService propertyService;

    @PostMapping("/property")
    public ResponseEntity<APIResponse> createProperty(@Valid  @RequestBody PropertyRequest propertyRequest) {
        List<PropertyResponse> propertyResponses = propertyService.create(propertyRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(propertyResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/properties")
    public ResponseEntity<APIResponse> updateProperty(@Valid @RequestBody PropertyRequest propertyRequest) {
        List<PropertyResponse> propertyResponses = propertyService.update(propertyRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(propertyResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/property/{id}")
    public ResponseEntity<APIResponse> deleteProperty(@PathVariable Long id) {
        List<PropertyResponse> propertyResponses = propertyService.deleteProperty(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(propertyResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/properties/{id}")
    public ResponseEntity<APIResponse>getSelectedProperty(@PathVariable Long id){
        PropertyResponse propertyResponse=propertyService.getSelectedProperty(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(propertyResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
