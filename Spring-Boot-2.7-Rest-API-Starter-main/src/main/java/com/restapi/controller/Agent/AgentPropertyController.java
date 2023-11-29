package com.restapi.controller.Agent;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Agent;
import com.restapi.model.AppUser;
import com.restapi.model.Property;
import com.restapi.model.Role;
import com.restapi.repository.AgentRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.PropertyRequest;
import com.restapi.response.PropertyResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.PropertyService;
import com.restapi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

    @Autowired
    private StorageService storageService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AgentRepository agentRepository;

    @PostMapping(value = "/property/{categoryId}/{agentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse> createLocation(@RequestParam("photo") MultipartFile photo,
                                                      @RequestParam("address") String address,
                                                      @RequestParam("propertyName") String propertyName,
                                                      @RequestParam("price") Double price,
                                                      @RequestParam("city") String city,
                                                      @RequestParam("zipcode") Long zipcode,@PathVariable Long categoryId,@PathVariable Long agentId) throws IOException {
        String file = storageService.storeFile(photo);
        PropertyRequest propertyRequest=new PropertyRequest();
        propertyRequest.setPropertyName(propertyName);
        propertyRequest.setPrice(price);
        propertyRequest.setAddress(address);
        propertyRequest.setPhoto(file);
        propertyRequest.setCity(city);
        propertyRequest.setZipcode(zipcode);
        List<PropertyResponse> propertyResponses = propertyService.create(propertyRequest,categoryId,agentId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(propertyResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws IOException {

        File file = propertyService.getFile(id);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
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
