package com.sakinr.airportreservationsystem.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

@Slf4j
@Configuration
@Profile("!test")
public class DataInitializerConfig {

    private final DataSource dataSource;
    private final boolean generateTestData;

    public DataInitializerConfig(DataSource dataSource, 
                               @Value("${app.generate-test-data:false}") boolean generateTestData) {
        this.dataSource = dataSource;
        this.generateTestData = generateTestData;
    }

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            try (Connection conn = dataSource.getConnection()) {
                log.info("Starting database initialization...");
                
                // Only generate test data if enabled
                if (generateTestData) {
                    log.info("Generating test data...");
                    executeSqlScript("generate_test_data.sql");
                    log.info("Test data generation completed.");
                }
                
                log.info("Database initialization completed successfully!");
            } catch (Exception e) {
                log.error("Error initializing database: ", e);
                throw new RuntimeException("Failed to initialize database", e);
            }
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
