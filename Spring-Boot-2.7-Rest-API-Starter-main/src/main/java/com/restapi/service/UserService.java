package com.restapi.service;

import com.restapi.dto.AgentDto;
import com.restapi.dto.AuthDto;
import com.restapi.dto.AuthDto;
import com.restapi.dto.CustomerDto;
import com.restapi.exception.common.AppException;
import com.restapi.exception.common.InvalidUserException;
import com.restapi.model.Agent;
import com.restapi.model.AppUser;
import com.restapi.model.Customer;
import com.restapi.model.Role;
import com.restapi.repository.AgentRepository;
import com.restapi.repository.CustomerRepository;
import com.restapi.repository.RoleRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.LoginRequest;
import com.restapi.request.RegisterRequest;
import com.restapi.response.AuthResponse;
import com.restapi.response.RoleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private AuthDto authDto;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthResponse register(RegisterRequest registerRequest) {
        AppUser appUser = authDto.mapToAppUser(registerRequest);
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUser.setRoles(roleRepository.findByName(registerRequest.getRole()));
        appUser = userRepository.save(appUser);
        if(registerRequest.getRole().equals("USER")){
            Customer customer= CustomerDto.maptoCustomer(registerRequest,appUser);
            customerRepository.save(customer);
        }
        if(registerRequest.getRole().equals("AGENT")){
            Agent agent= AgentDto.mapToAgent(registerRequest,appUser);
            agentRepository.save(agent);
        }
        return authDto.mapToAuthResponse(appUser);
    }

    public AuthResponse login(LoginRequest loginRequest) {
        AppUser appUser = userRepository
                .findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new InvalidUserException("Invalid user credentials"));

        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), appUser.getPassword())) {
            throw new InvalidUserException("Invalid password");
        }

        return authDto.mapToAuthResponse(appUser);
    }

    public List<RoleResponse> getRoles() {
        List<Role> roleList=roleRepository.findAll();
        List<RoleResponse> roleResponses=authDto.maptoRole(roleList);
        return roleResponses;
    }
}
