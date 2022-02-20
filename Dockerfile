FROM openjdk:8-jdk-alpine

MAINTAINER Ramazan Sakin <ramazansakin63@gmail.com>
EXPOSE 8080
ADD target/airport-reservation-system.jar airport-reservation-system.jar

ENTRYPOINT ["java","-jar","/airport-reservation-system.jar"]

## Dockerizing the app
#
# Create a Spring Boot Application
# Create Dockerfile
# Build executable jar file - mvn clean package
# Build Docker image - docker build -t airport-reservation-app:v1 .
# Run Docker container using the image built - docker run -d --name bootdocker -p 8080:8080 airport-reservation-app:v1
# Test