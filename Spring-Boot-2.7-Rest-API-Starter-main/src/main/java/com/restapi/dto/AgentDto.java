package com.restapi.dto;

import com.restapi.model.Agent;
import com.restapi.model.AppUser;
import com.restapi.model.Property;
import com.restapi.request.AgentRequest;
import com.restapi.request.RegisterRequest;
import com.restapi.response.AgentPropertyResponse;
import com.restapi.response.AgentResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Component
public class AgentDto {

    public static Agent mapToAgent(RegisterRequest registerRequest, AppUser appUser) {
        Agent agent=new Agent();
        agent.setAgentName(registerRequest.getName());
        agent.setContactNumber(registerRequest.getContact());
        agent.setAppUser(appUser);
        return agent;

    }

    public Agent maptoAgent(AgentRequest agentRequest) {
        Agent agent=new Agent();
        agent.setAgentName(agentRequest.getAgentName());
        agent.setContactNumber(agentRequest.getContactNumber());
        return agent;
    }

    public List<AgentResponse> maptoAgentResponse(List<Agent> agentList) {
        List<AgentResponse> agentResponses = new ArrayList<>();
        for(Agent agent:agentList)
        {
            AgentResponse agentResponse = new AgentResponse();
            agentResponse.setAgentName(agent.getAgentName());
            agentResponse.setContactNumber(agent.getContactNumber());
            agentResponses.add(agentResponse);
        }
        return agentResponses;
    }

    public List<AgentPropertyResponse> maptoAgentPropertyResponse(List<Property> propertyList,List<Agent> agentList) {
        List<AgentPropertyResponse> agentPropertyResponses=new ArrayList<>();
        for(Agent agent:agentList){
            AgentPropertyResponse agentPropertyResponse=new AgentPropertyResponse();
            agentPropertyResponse.setId(agent.getId());
            agentPropertyResponse.setAgentName(agent.getAgentName());
            agentPropertyResponse.setContactNumber(agent.getContactNumber());
            agentPropertyResponse.setPropertyList(agent.getPropertyList());
            agentPropertyResponses.add(agentPropertyResponse);
        }
        return agentPropertyResponses;
    }
}
