package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterRequest {

    @NotEmpty
    @Size(min = 3, message = "Username should have at least 3 characters")
    private String username;

    @NotEmpty
    @Size(min = 4, message = "Password should have at least 4 characters")
    private String password;

    @NotEmpty
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @NotEmpty
    @Size(min = 10, message = "contact should have at least 10 characters")
    private Long contact;

    @Size(min = 2, message = "role should have at least 2 characters")
    private String role;




}
