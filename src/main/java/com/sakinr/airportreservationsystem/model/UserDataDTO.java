package com.sakinr.airportreservationsystem.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

@Data
public class UserDataDTO implements Serializable {

    @ApiModelProperty(position = 0)
    private String username;

    @ApiModelProperty(position = 1)
    @Email(message = "Email not valid")
    private String email;

    @ApiModelProperty(position = 2)
    private String password;

    @ApiModelProperty(position = 3)
    List<Role> roles;

}
