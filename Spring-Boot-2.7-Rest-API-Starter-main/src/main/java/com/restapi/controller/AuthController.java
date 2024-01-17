package com.restapi.controller;

import com.restapi.request.LoginRequest;
import com.restapi.request.RegisterRequest;
import com.restapi.response.AuthResponse;
import com.restapi.response.RoleResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private UserService userService;



    //endpoint for user login
    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        AuthResponse loggedInUser = userService.login(loginRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(loggedInUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //endpoint for user registration
    @PostMapping("/register")
    public ResponseEntity<APIResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        AuthResponse registeredUser = userService.register(registerRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(registeredUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //endpoint to get all roles
    @GetMapping("/role")
    public  ResponseEntity<APIResponse> getRoles() {
        List<RoleResponse> getRole = userService.getRoles();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(getRole);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //endpoint to get information about all users
    @GetMapping("/allUsers")
    public ResponseEntity<APIResponse> getAllUsers(){
        List<AuthResponse> registeredUser = userService.findAllUsers();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(registeredUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
