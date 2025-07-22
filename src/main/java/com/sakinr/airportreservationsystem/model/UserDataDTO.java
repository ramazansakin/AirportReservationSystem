package com.sakinr.airportreservationsystem.model;


import jakarta.validation.constraints.Email;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDataDTO implements Serializable {

    private String username;

    @Email(message = "Email not valid")
    private String email;

    private String password;

    List<Role> roles;

}
