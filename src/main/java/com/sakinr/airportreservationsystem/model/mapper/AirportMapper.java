package com.sakinr.airportreservationsystem.model.mapper;

import com.sakinr.airportreservationsystem.entity.Airport;
import com.sakinr.airportreservationsystem.model.dto.AirportDTO;

public class AirportMapper {

    public static AirportDTO toDto(Airport airport) {
        AirportDTO converted = new AirportDTO();
        converted.setName(airport.getName());
        converted.setAddresses(airport.formatToAddressList());
        return converted;
    }

    public static Airport toAirport(AirportDTO airportDTO) {
        Airport converted = new Airport();
        converted.setName(airportDTO.getName());
        converted.setAddress(airportDTO.formatAddresses());
        return converted;
    }

}
