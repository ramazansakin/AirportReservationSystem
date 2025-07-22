package com.sakinr.airportreservationsystem.model;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class Address {
    @NotBlank
    private String city;
    @NotBlank
    private String province;
    private String streetCode;
    private Integer buildingNo;

    public Address(String city, String province) {
        this.city = city;
        this.province = province;
    }

    public String dbFormat() {
        return city + "/" + province;
    }
}
