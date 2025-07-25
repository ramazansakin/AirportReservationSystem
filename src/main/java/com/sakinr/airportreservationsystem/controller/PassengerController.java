package com.sakinr.airportreservationsystem.controller;

import com.sakinr.airportreservationsystem.entity.Passenger;
import com.sakinr.airportreservationsystem.exception.InvalidRequestException;
import com.sakinr.airportreservationsystem.model.dto.PassengerDto;
import com.sakinr.airportreservationsystem.model.mapper.PassengerMapper;
import com.sakinr.airportreservationsystem.service.PassengerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/passengers")
public class PassengerController {

    private final PassengerService passengerService;
    private static final PassengerMapper PASSENGER_MAPPER = Mappers.getMapper(PassengerMapper.class);


    @GetMapping
    public List<PassengerDto> getAllPassengers() {
        List<Passenger> allPassengers = passengerService.getAllPassengers();
        return allPassengers.stream().map(PASSENGER_MAPPER::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public PassengerDto getPassenger(@PathVariable @Min(1) Integer id) {
        return PASSENGER_MAPPER.toDto(passengerService.getPassenger(id));
    }

    @PostMapping(value = "/create")
    public void savePassenger(@Valid @RequestBody PassengerDto passenger) {
        passengerService.addPassenger(PASSENGER_MAPPER.toEntity(passenger));
    }

    @PutMapping(value = "/update")
    public Passenger updatePassenger(@Valid @RequestBody Passenger passenger) {
        if (passenger.getId() == null) {
            throw new InvalidRequestException("Passenger id can not be null for update!");
        }
        return passengerService.updatePassenger(passenger);
    }

    @DeleteMapping(value = "/delete")
    public boolean deletePassenger(@RequestParam @Min(1) Integer id) {
        return passengerService.deletePassenger(id);
    }

}
