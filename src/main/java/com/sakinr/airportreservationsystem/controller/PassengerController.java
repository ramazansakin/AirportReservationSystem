package com.sakinr.airportreservationsystem.controller;

import com.sakinr.airportreservationsystem.entity.Passenger;
import com.sakinr.airportreservationsystem.model.dto.PassengerDto;
import com.sakinr.airportreservationsystem.model.mapper.PassengerMapper;
import com.sakinr.airportreservationsystem.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/passenger")
public class PassengerController {

    private final PassengerService passengerService;
    private static final PassengerMapper PASSENGER_MAPPER = Mappers.getMapper(PassengerMapper.class);

    @GetMapping
    public String welcome() {
        return "Welcome to Passenger Service!";
    }

    @GetMapping(value = "/all")
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
        return passengerService.updatePassenger(passenger);
    }

    @DeleteMapping(value = "/delete")
    public boolean deletePassenger(@RequestParam @Min(1) Integer id) {
        return passengerService.deletePassenger(id);
    }

}
