package com.restapi.controller.admin;

import com.restapi.model.Property;
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

    @GetMapping
    public ResponseEntity<APIResponse> getAllProperties() {
        List<Property> propertyList = propertyService.findAll().getPropertyList();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(propertyList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{propertyId}")
    public ResponseEntity<APIResponse> allowProperty(@PathVariable Integer propertyId) {
        PropertyResponse propertyResponse = adminService.allowProperty(propertyId);
        return getAllProperties();
    }
}
