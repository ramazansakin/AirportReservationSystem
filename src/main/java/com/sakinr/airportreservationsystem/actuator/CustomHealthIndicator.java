package com.sakinr.airportreservationsystem.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

    @Value("${info.app.version}")
    private String appVersion;

    @Override
    protected void doHealthCheck(Health.Builder builder) {
        // Use the builder to build the health status details that should be reported.
        // If you throw an exception, the status will be DOWN with the exception message.
        builder.up()
                .withDetail("app", "Up and Running")
                .withDetail("error", "Nothing! I'm perfect.")
                .withDetail("appVersion", appVersion);
    }
}
