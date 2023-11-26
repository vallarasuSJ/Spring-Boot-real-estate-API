package com.restapi.response;

import com.restapi.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AgentResponse {
    private Long contactNumber;
    private String agentName;

}
