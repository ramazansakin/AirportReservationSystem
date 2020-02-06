package com.sakinr.airportreservationsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@DynamicUpdate
@Table(name = "passenger")
public class Passenger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String firstname;
    private String lastname;
    private String gender;
    private Integer age;
    private String phone;

    @JsonManagedReference
    @OneToOne(mappedBy = "passenger",fetch = FetchType.LAZY, optional = false)
    private Ticket ticket_id;

}
