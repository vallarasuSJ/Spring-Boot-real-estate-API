package com.restapi.service;

import com.restapi.dto.AgentDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Address;
import com.restapi.model.Agent;
import com.restapi.model.AppUser;
import com.restapi.model.Property;
import com.restapi.repository.AddressRepository;
import com.restapi.repository.AgentRepository;
import com.restapi.repository.PropertyRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.AgentRequest;
import com.restapi.response.AgentPropertyResponse;
import com.restapi.response.AgentResponse;
import com.restapi.response.PropertyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AgentDto agentDto;

    public List<AgentResponse> createAgent(AgentRequest agentRequest) {
        Optional<Agent> optionalAgent=agentRepository.findById(agentRequest.getUserId());
        if(optionalAgent.isEmpty()){
            Agent agent=agentDto.maptoAgent(agentRequest);
            AppUser appUser=userRepository.findById(agentRequest.getUserId())
                    .orElseThrow(()->new ResourceNotFoundException("userId","userId", agentRequest.getUserId()));
            agent.setAppUser(appUser);
            agentRepository.save(agent);
            return findAll();
        }
        return null;
    }


    public List<AgentResponse> findAll() {
        List<Agent> agentList = agentRepository.findAll();
        List<AgentResponse> agentResponses = agentDto.maptoAgentResponse(agentList);
        return agentResponses;
    }

    public List<AgentResponse> deleteAgent(Long id) {
        agentRepository.deleteById(id);
        return findAll();
    }

    public List<AgentPropertyResponse> findAllAgentsProperties() {
        List<Property> propertyList=propertyRepository.findAll();
        List<Agent> agentList=agentRepository.findAll();
        List<AgentPropertyResponse> agentPropertyResponses=agentDto.maptoAgentPropertyResponse(propertyList,agentList);
        return  agentPropertyResponses;
    }
}
