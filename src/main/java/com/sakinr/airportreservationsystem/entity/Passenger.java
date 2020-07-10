package com.sakinr.airportreservationsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "passenger")
public class Passenger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "firstname can not be null")
    private String firstname;

    @NotNull(message = "lastname can not be null")
    private String lastname;

    @NotNull(message = "gender can not be null")
    private String gender;

    @NotNull(message = "age can not be null")
    private Integer age;

    @NotNull(message = "phone can not be null")
    private String phone;

}
