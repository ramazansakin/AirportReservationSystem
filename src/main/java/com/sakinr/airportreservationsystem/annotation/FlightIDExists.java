package com.sakinr.airportreservationsystem.annotation;

import com.sakinr.airportreservationsystem.annotation.validator.FlightIDExistingValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = FlightIDExistingValidator.class)

public @interface FlightIDExists {
    String message() default "{validation.messages.flight.id.exists}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
