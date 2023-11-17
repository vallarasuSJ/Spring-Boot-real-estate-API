package com.restapi.service;

import com.restapi.dto.AgentDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Address;
import com.restapi.model.Agent;
import com.restapi.model.AppUser;
import com.restapi.repository.AddressRepository;
import com.restapi.repository.AgentRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.AgentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AgentDto agentDto;

    public Agent createAgent(AgentRequest agentRequest) {
        Agent agent=agentDto.maptoAgent(agentRequest);
        AppUser appUser=userRepository.findById(agentRequest.getUserId())
                        .orElseThrow(()->new ResourceNotFoundException("userId","userId", agentRequest.getUserId()));
        agent.setAppUser(appUser);
        Address address=addressRepository.findById(agentRequest.getAddressId())
                        .orElseThrow(()->new ResourceNotFoundException("addressId","addressId", agentRequest.getAddressId()));
        agent.setAddress(address);
        agentRepository.save(agent);
        return agent;
    }


    public List<Agent> findAll() {
        return agentRepository.findAll();
    }
}
