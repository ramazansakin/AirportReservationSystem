package com.sakinr.airportreservationsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "route")
public class Route implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "departure_airport_id", referencedColumnName = "id")
    private Airport departure_airport;

    @Embedded
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "arrival_airport_id", referencedColumnName = "id")
    private Airport arrival_airport;

}
