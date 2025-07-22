# Build stage
FROM maven:3.9.5-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-alpine

LABEL maintainer="Ramazan Sakin <ramazansakin63@gmail.com>"

# Add the application jar to the container
COPY --from=build /app/target/airport-reservation-system.jar /app/airport-reservation-system.jar

# Expose the application port
EXPOSE 8080

# Set the entry point
ENTRYPOINT ["java", "-jar", "/app/airport-reservation-system.jar"]

## Docker Commands
#
# Build the application and Docker image:
#   mvn clean package && docker build -t airport-reservation-app:latest .
#
# Run the container:
#   docker run -d --name airport-app -p 8080:8080 airport-reservation-app:latest
#
# Run with docker-compose:
#   docker-compose up -d