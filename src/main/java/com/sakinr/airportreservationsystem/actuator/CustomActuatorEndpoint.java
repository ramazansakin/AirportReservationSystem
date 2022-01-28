package com.sakinr.airportreservationsystem.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "release-notes")
public class CustomActuatorEndpoint {

    String version2 = "** Version 2.0 ** \n\n"
            + "## Added"
            + "* Homepage added \n"
            + "* Item creation form added \n"
            + "* View the watchlsit page added \n"
            + "## Fixed \n"
            + "* docker integration problem solved \n"
            + "## Updated \n"
            + "* /info actuator api updated with details";

    @ReadOperation
    public String releaseNotes() {
        return version2;
    }
}
