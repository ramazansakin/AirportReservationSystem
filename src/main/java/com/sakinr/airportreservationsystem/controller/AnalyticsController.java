package com.sakinr.airportreservationsystem.controller;

import com.sakinr.airportreservationsystem.repository.AnalyticsRepository;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/analytics")
public class AnalyticsController {

    private final AnalyticsRepository analyticsRepository;

    @GetMapping("/busiest-routes")
    public ResponseEntity<List<Map<String, Object>>> getBusiestRoutes() {
        return ResponseEntity.ok(analyticsRepository.findBusiestRoutes());
    }

    @GetMapping("/most-profitable-flights")
    public ResponseEntity<List<Map<String, Object>>> getMostProfitableFlights() {
        return ResponseEntity.ok(analyticsRepository.findMostProfitableFlights());
    }

    @GetMapping("/top-airports")
    public ResponseEntity<List<Map<String, Object>>> getTopAirports() {
        return ResponseEntity.ok(analyticsRepository.findTopAirportsByPassengerTraffic());
    }

    @GetMapping("/top-airlines")
    public ResponseEntity<List<Map<String, Object>>> getTopAirlines() {
        return ResponseEntity.ok(analyticsRepository.findTopAirlines());
    }

    @GetMapping("/daily-stats")
    public ResponseEntity<List<Map<String, Object>>> getDailyStats(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        // Default to last 30 days if no dates provided
        Date defaultEndDate = new Date();
        Date defaultStartDate = Date.from(Instant.now().minus(30, ChronoUnit.DAYS));

        Date effectiveStartDate = startDate != null ? startDate : defaultStartDate;
        Date effectiveEndDate = endDate != null ? endDate : defaultEndDate;

        return ResponseEntity.ok(analyticsRepository.getDailyFlightStats(effectiveStartDate, effectiveEndDate));
    }

    @GetMapping("/passenger-history")
    public ResponseEntity<List<Map<String, Object>>> getPassengerHistory() {
        return ResponseEntity.ok(analyticsRepository.getPassengerFlightHistory());
    }

    @GetMapping("/monthly-route-performance")
    public ResponseEntity<List<Map<String, Object>>> getMonthlyRoutePerformance(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "50") @Min(1) @Max(200) int size) {
        List<Map<String, Object>> results = analyticsRepository.findMonthlyRoutePerformanceAnalytics();

        // Manual pagination since we're using native queries
        int start = page * size;
        int end = Math.min(start + size, results.size());

        if (start >= results.size()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        return ResponseEntity.ok(results.subList(start, end));
    }

    @GetMapping("/customer-analytics")
    public ResponseEntity<List<Map<String, Object>>> getCustomerAnalytics(
            @RequestParam(required = false) String tier,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "50") @Min(1) @Max(200) int size) {

        List<Map<String, Object>> results = analyticsRepository.findCustomerAnalyticsWithTiers();

        // Filter by customer tier if specified
        if (tier != null && !tier.isEmpty()) {
            results = results.stream()
                    .filter(customer -> tier.equalsIgnoreCase((String) customer.get("customerTier")))
                    .collect(Collectors.toList());
        }

        // Manual pagination
        int start = page * size;
        int end = Math.min(start + size, results.size());

        if (start >= results.size()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        return ResponseEntity.ok(results.subList(start, end));
    }


}
