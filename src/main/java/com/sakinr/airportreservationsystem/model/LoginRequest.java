package com.sakinr.airportreservationsystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO class for login requests
 */
@Data
public class LoginRequest {
    
    @NotBlank(message = "Username cannot be blank")
    @Schema(description = "Username of the user", example = "john.doe", required = true)
    private String username;
    
    @NotBlank(message = "Password cannot be blank")
    @Schema(description = "Password of the user", example = "securePassword123", required = true)
    private String password;
}
