package com.sakinr.airportreservationsystem.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 5, max = 25, message = "username length should be between 4 and 50 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    public List<Role> roles;

}
