package com.sakinr.airportreservationsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "flight")
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column
    private String departure_date;

    @Column
    private String estimated_arrival_date;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Passenger route;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "airport_company_id")
    private AirportCompany airportCompany;

}
