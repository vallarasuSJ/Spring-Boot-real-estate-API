package com.restapi.controller;

import com.restapi.model.Agent;
import com.restapi.model.AppUser;
import com.restapi.model.Property;
import com.restapi.model.Role;
import com.restapi.request.AddressRequest;
import com.restapi.request.AgentRequest;
import com.restapi.request.PropertyRequest;
import com.restapi.response.AddressResponse;
import com.restapi.response.PropertyResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AddressService;
import com.restapi.service.AgentService;
import com.restapi.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/agent")
@RolesAllowed(Role.AGENT)
public class AgentController {

    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AgentService agentService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAgentDetails() {
        List<Agent> agentList = agentService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(agentList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> CreateAgent(@Valid @RequestBody AgentRequest agentRequest) {
        Agent agent = agentService.createAgent(agentRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(agent);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/address")
    public ResponseEntity<APIResponse> createAddress(@Valid @RequestBody AddressRequest addressRequest) {
        AddressResponse addressResponse = addressService.create(addressRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(addressResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }




}
