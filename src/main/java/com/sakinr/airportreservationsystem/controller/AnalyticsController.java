package com.sakinr.airportreservationsystem.controller;

import com.sakinr.airportreservationsystem.repository.AnalyticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/query")
    public ResponseEntity<?> executeCustomQuery(
            @RequestParam String queryName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return switch (queryName.toLowerCase()) {
            case "busiest-routes" -> getBusiestRoutes();
            case "most-profitable-flights" -> getMostProfitableFlights();
            case "top-airports" -> getTopAirports();
            case "top-airlines" -> getTopAirlines();
            case "daily-stats" -> {
                if (startDate == null || endDate == null) {
                    throw new IllegalArgumentException("startDate and endDate are required for daily-stats");
                }
                yield getDailyStats(startDate, endDate);
            }
            case "passenger-history" -> getPassengerHistory();
            default -> ResponseEntity.badRequest().body(Map.of("error", "Unknown query name"));
        };
    }
}
