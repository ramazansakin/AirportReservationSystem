package com.sakinr.airportreservationsystem.resttemplate.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestClientResult {
    private Integer status;
    private Object responseBody;
}
