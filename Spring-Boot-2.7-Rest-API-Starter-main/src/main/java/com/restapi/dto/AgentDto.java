package com.restapi.dto;

import com.restapi.model.Agent;
import com.restapi.request.AgentRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class AgentDto {

    public Agent maptoAgent(AgentRequest agentRequest) {
        Agent agent=new Agent();
        agent.setAgentName(agentRequest.getAgentName());
        return agent;
    }
}
