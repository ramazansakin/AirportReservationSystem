package com.sakinr.airportreservationsystem.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String cityName;
    private String streetCode;
    private Integer buildingNo;
}
