package com.sakinr.airportreservationsystem.resttemplate.orgpro;

import com.sakinr.airportreservationsystem.resttemplate.dto.EventDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/event-app-rest-client")
public class OrganizationManagementProjectRestClient {

    private static final String blogAppBaseURL = "http://localhost:8085";
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "/events")
    public ResponseEntity<Object> getAllEvents() {
        final String uri = blogAppBaseURL + "/events";

        ResponseEntity<List<EventDTO>> result = restTemplate.exchange(uri, HttpMethod.GET, getHeader(),
                new ParameterizedTypeReference<List<EventDTO>>() {
                });
        return new ResponseEntity<>(result.getBody(), result.getStatusCode());
    }

    @GetMapping(value = "/events/{id}")
    public ResponseEntity<Object> getEventById(@PathVariable Long id) {
        final String uri = blogAppBaseURL + "/events/" + id;

        ResponseEntity<EventDTO> result = restTemplate.exchange(uri, HttpMethod.GET, getHeader(),
                new ParameterizedTypeReference<EventDTO>() {
                });
        return new ResponseEntity<>(result.getBody(), result.getStatusCode());
    }

    @PostMapping(value = "/events")
    public ResponseEntity<Object> createEvent(@RequestBody EventDTO event) {
        final String uri = blogAppBaseURL + "/events";

        HttpEntity<EventDTO> request = new HttpEntity<>(event);
        ResponseEntity<EventDTO> result = restTemplate.exchange(uri, HttpMethod.POST, request,
                new ParameterizedTypeReference<EventDTO>() {
                });
        return new ResponseEntity<>(result.getBody(), result.getStatusCode());
    }

    @DeleteMapping(value = "/events/{id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable Long id) {
        final String uri = blogAppBaseURL + "/events/" + id;

        ResponseEntity<Map<String, Boolean>> result = restTemplate.exchange(uri, HttpMethod.DELETE, getHeader(),
                new ParameterizedTypeReference<Map<String, Boolean>>() {
                }
        );
        return new ResponseEntity<>(result.getBody(), result.getStatusCode());
    }

    private HttpEntity<String> getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return entity;
    }

}
