package com.sakinr.airportreservationsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "departure_airport", referencedColumnName = "id")
    private Airport departure_airport;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "arrival_airport", referencedColumnName = "id")
    private Airport arrival_airport;

}
