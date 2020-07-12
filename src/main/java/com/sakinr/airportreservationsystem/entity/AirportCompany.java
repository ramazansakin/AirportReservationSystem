package com.sakinr.airportreservationsystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "airport_company")
public class AirportCompany implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "name can not be null")
    private String name;

    @Embedded
    @JsonManagedReference
    @OneToMany(mappedBy = "airportCompany", cascade = CascadeType.MERGE)
    private List<Flight> flights;

}
