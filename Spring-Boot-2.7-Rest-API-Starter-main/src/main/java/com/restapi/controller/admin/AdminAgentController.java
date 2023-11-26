package com.restapi.controller.admin;

import com.restapi.model.Agent;
import com.restapi.model.Role;
import com.restapi.response.AgentPropertyResponse;
import com.restapi.response.AgentResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AddressService;
import com.restapi.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/admin/agent")
@RolesAllowed(Role.ADMIN)
public class AdminAgentController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AgentService agentService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAgentDetails() {
        List<AgentResponse> agentResponses = agentService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(agentResponses);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/properties/all")
    public ResponseEntity<APIResponse> getAgentPropertyDetails(){
        List<AgentPropertyResponse> agentPropertyResponses=agentService.findAllAgentsProperties();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(agentPropertyResponses);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }


}
