package com.restapi.response;

import com.restapi.model.Property;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AgentPropertyResponse {
    private Long contactNumber;
    private String agentName;
    private Long id;
    private List<Property> propertyList;
}
