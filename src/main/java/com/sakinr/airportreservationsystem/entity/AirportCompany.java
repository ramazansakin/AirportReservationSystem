package com.sakinr.airportreservationsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "airport_company")
public class AirportCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "company_name")
    private String name;

    @OneToMany(mappedBy = "airportCompany", cascade = {
            CascadeType.ALL
    })
    private List<Flight> flights;

}
