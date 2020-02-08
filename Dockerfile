FROM openjdk:8-jdk-alpine

MAINTAINER Ramazan Sakin <ramazansakin63@gmail.com>
EXPOSE 8090
ADD target/airport-reservation-system.jar airport-reservation-system.jar

ENTRYPOINT ["java","-jar","/airport-reservation-system.jar"]