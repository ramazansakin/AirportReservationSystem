package com.sakinr.airportreservationsystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object containing authentication details and user information")
public class LoginResponse {
    
    @Schema(description = "JWT token for authenticated requests", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;
    
    @Schema(description = "Token type (usually 'Bearer')", example = "Bearer")
    private String tokenType = "Bearer";
    
    @Schema(description = "User ID", example = "1")
    private Integer id;
    
    @Schema(description = "Username", example = "john.doe")
    private String username;
    
    @Schema(description = "User email", example = "john.doe@example.com")
    private String email;
    
    @Schema(description = "List of user roles", example = "[ROLE_USER]")
    private List<Role> roles;
    
    public LoginResponse(String token, Integer id, String username, String email, List<Role> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
