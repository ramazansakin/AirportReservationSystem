package com.sakinr.airportreservationsystem;

import com.sakinr.airportreservationsystem.filter.ExceptionHandlerFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AirportReservationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirportReservationSystemApplication.class, args);
    }

    // unregister any filter component
    @Bean
    public FilterRegistrationBean<ExceptionHandlerFilter> registration(ExceptionHandlerFilter filter) {
    	// exception management handling manageable via both ExceptionHandlerFilter
		// and GenericExceptionHandler interceptor, we can choose any of them
		// I choose to stop filter
        FilterRegistrationBean<ExceptionHandlerFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }

}
