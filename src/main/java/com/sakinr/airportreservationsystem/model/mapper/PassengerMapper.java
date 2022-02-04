package com.sakinr.airportreservationsystem.model.mapper;

import com.sakinr.airportreservationsystem.entity.Passenger;
import com.sakinr.airportreservationsystem.model.dto.PassengerDto;
import org.mapstruct.Mapper;

@Mapper
public interface PassengerMapper {

    PassengerDto toDto(Passenger entity);

    Passenger toEntity(PassengerDto dto);
}
