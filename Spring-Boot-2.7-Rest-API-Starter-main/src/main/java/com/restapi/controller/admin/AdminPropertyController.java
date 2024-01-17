package com.restapi.controller.admin;

import com.restapi.model.Property;
import com.restapi.request.PropertyRequest;
import com.restapi.response.PropertyResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AdminService;
import com.restapi.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/property")
public class AdminPropertyController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private AdminService adminService;

    @Autowired
    private PropertyService propertyService;

    //retrieves all the property details
    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllProperties() {
        List<PropertyResponse> propertyList = propertyService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(propertyList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    //Updates the approval status of a property based on the provided PropertyRequest.
    @PutMapping("/update")
    public ResponseEntity<APIResponse> allowProperty(@RequestBody PropertyRequest propertyRequest) {
        adminService.allowProperty(propertyRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
