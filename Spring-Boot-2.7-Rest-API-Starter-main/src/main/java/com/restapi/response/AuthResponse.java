package com.restapi.response;

import com.restapi.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthResponse {
    private Long id;
    private String username;
    private String name;
    private String role;
    private List<Role> roleList;
}
