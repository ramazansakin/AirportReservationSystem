package com.sakinr.airportreservationsystem.config;

import com.sakinr.airportreservationsystem.interceptor.SampleAirportInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@RequiredArgsConstructor
@Component
public class InterceptorConfig implements WebMvcConfigurer {

    private final SampleAirportInterceptor sampleAirportInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sampleAirportInterceptor).addPathPatterns("/api/airport/*");
    }
}
