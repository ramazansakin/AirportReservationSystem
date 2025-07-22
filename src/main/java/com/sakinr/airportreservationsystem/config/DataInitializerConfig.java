package com.sakinr.airportreservationsystem.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * Configuration class that initializes test data in the database.
 * This only runs when the 'test' profile is active.
 */
@Slf4j
@Configuration
@Profile("test") // Only run in test profile
@RequiredArgsConstructor
public class DataInitializerConfig {

    private final DataSource dataSource;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            log.info("Starting test database initialization...");
            
            // Always generate test data in test profile
            log.info("Generating test data...");
            executeSqlScript("generate_test_data.sql");
            log.info("Test data generation completed.");
            
            log.info("Test database initialization completed successfully!");
        };
    }
    
    private void executeSqlScript(String scriptName) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setContinueOnError(true);
        populator.setSeparator(";");
        populator.setSqlScriptEncoding("UTF-8");
        populator.addScript(new ClassPathResource(scriptName));
        
        try {
            populator.execute(Objects.requireNonNull(dataSource));
        } catch (Exception e) {
            log.error("Error executing SQL script: " + scriptName, e);
            throw new RuntimeException("Failed to execute SQL script: " + scriptName, e);
        }
    }
}
