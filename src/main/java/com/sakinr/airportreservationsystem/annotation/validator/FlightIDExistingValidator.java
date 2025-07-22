package com.sakinr.airportreservationsystem.annotation.validator;

import com.sakinr.airportreservationsystem.annotation.FlightIDExists;
import com.sakinr.airportreservationsystem.entity.Flight;
import com.sakinr.airportreservationsystem.exception.NotFoundException;
import com.sakinr.airportreservationsystem.service.FlightService;
import lombok.RequiredArgsConstructor;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class FlightIDExistingValidator implements ConstraintValidator<FlightIDExists, Long> {

    private final FlightService flightService;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        // if not exists, it returns not found exception
        try {
            flightService.getFlight(id.intValue());
        } catch (NotFoundException e) {
            return true;
        }
        return false;
    }
}
