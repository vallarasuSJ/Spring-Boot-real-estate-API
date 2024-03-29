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

    //creating new property
//    @PostMapping(value = "/property/{categoryId}/{agentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<APIResponse> createProperty(@RequestParam("photo") MultipartFile photo,
//                                                      @RequestParam("address") String address,
//                                                      @RequestParam("propertyName") String propertyName,
//                                                      @RequestParam("price") Double price,
//                                                      @RequestParam("city") String city,
//                                                      @RequestParam("zipcode") Long zipcode, @PathVariable Long categoryId, @PathVariable Long agentId) throws IOException {
//       //store file in the storage service
//        String file = storageService.storeFile(photo);
//
//        // Create PropertyRequest object
//        PropertyRequest propertyRequest = new PropertyRequest();
//        propertyRequest.setPropertyName(propertyName);
//        propertyRequest.setPrice(price);
//        propertyRequest.setAddress(address);
//        propertyRequest.setPhoto(file);
//        propertyRequest.setCity(city);
//        propertyRequest.setZipcode(zipcode);
//        List<PropertyResponse> propertyResponses = propertyService.create(propertyRequest, categoryId, agentId);
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setData(propertyResponses);
//        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//    }

    //endpoint to add property
    @PostMapping(value = "/property/{categoryId}/{agentId}",consumes ="multipart/form-data",produces = "application/json")
    public ResponseEntity<APIResponse> createProperty(@ModelAttribute PropertyRequest propertyRequest, @PathVariable Long categoryId, @PathVariable Long agentId) throws IOException {
        //store file in the storage service
        String file = storageService.storeFile(propertyRequest.getImage());
        propertyRequest.setPhoto(file);
        propertyService.create(propertyRequest, categoryId, agentId);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//    //endpoint to update existing property based on property id and agent id
//    @PutMapping(value = "/properties/{id}/{agentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<APIResponse> updateProperty(
//                                                      @RequestParam("address") String address,
//                                                      @RequestParam("propertyName") String propertyName,
//                                                      @RequestParam("price") Double price,
//                                                      @RequestParam("city") String city,
//                                                      @RequestParam("zipcode") Long zipcode, @PathVariable Long agentId,@PathVariable Long id) throws IOException {
//
//        PropertyRequest propertyRequest = new PropertyRequest();
//        propertyRequest.setPropertyName(propertyName);
//        propertyRequest.setPrice(price);
//        propertyRequest.setAddress(address);
//        propertyRequest.setCity(city);
//        propertyRequest.setZipcode(zipcode);
//        AppUser appUser=userRepository.findById(agentId)
//                .orElseThrow(()->new ResourceNotFoundException("agentId","agentId",agentId));
//        propertyRequest.setAgentId(appUser.getAgent().getId());
//        apiResponse.setStatus(HttpStatus.OK.value());
//        List<PropertyResponse> propertyResponses = propertyService.update(propertyRequest,id);
//        apiResponse.setData(propertyResponses);
//        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//    }

    //endpoint to update existing property based on property id and agent id
    @PutMapping(value = "/properties/{id}/{agentId}",consumes ="multipart/form-data",produces = "application/json")
    public ResponseEntity<APIResponse> updateProperty(
            @ModelAttribute PropertyRequest propertyRequest, @PathVariable Long agentId,@PathVariable Long id) throws IOException {
        AppUser appUser=userRepository.findById(agentId)
                .orElseThrow(()->new ResourceNotFoundException("agentId","agentId",agentId));
        propertyRequest.setAgentId(appUser.getAgent().getId());
        apiResponse.setStatus(HttpStatus.OK.value());
        propertyService.update(propertyRequest,id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    //endpoint to download property file(image)
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


    //endpoint to delete the property by using ID
    @DeleteMapping("/property/{id}")
    public ResponseEntity<APIResponse> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/property/{id}")
    public ResponseEntity<APIResponse> getSelectedProperty(@PathVariable Long id) {
        PropertyResponse propertyResponse = propertyService.getSelectedProperty(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(propertyResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //endpoint to get the agent properties
    @GetMapping("/properties/{agentId}")
    public ResponseEntity<APIResponse> getAllProperties(@PathVariable Long agentId){
        List<PropertyResponse> propertyList=propertyService.findAgentProperties(agentId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(propertyList);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

}
