package com.sakinr.airportreservationsystem;

import com.sakinr.airportreservationsystem.filter.ExceptionHandlerFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
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

    // Swagger api documentation config for base package
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(
                RequestHandlerSelectors.basePackage("com.sakinr.airportreservationsystem")
        ).build();
    }

}
