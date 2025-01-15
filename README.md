REST API Service for Receiving Sensor Data

This project is a REST API service built using Spring Boot, designed to receive data from a sensor. The service provides functionality for sensor registration, data submission, and retrieval of weather-related measurements.

Features

1.Sensor Registration:

Endpoint: POST /sensors/registration

Registers a new sensor with a unique name.

Validates if the sensor name already exists and returns an error if it does.

Example JSON request:
{
    "name": "sensorName"
}
2.Add Measurement:

Endpoint: POST /measurements/add

Receives temperature and weather condition measurements from a sensor.

Validates the request data before saving.

Example JSON request:

{
    "value": 23.5,
    "raining": true,
    "sensor": {
        "name": "sensorName"
    }
}

3.Retrieve Measurements:

Endpoint: GET /measurements

Returns all recorded measurements.

4.Count Rainy Days:

Endpoint: GET /measurements/rainyDaysCount

Returns the total number of days with rainfall recorded.

5.JWT Authorization:

Secures endpoints with JWT-based authentication.

6.Client Application:

A Java client to interact with the API, perform sensor registration, send measurements, and retrieve data.

Setup Instructions

1. Build the Application

Run the following commands to build the application and generate the JAR file:
mvn clean package
2. Docker Setup 
Dockerfile

The Dockerfile is already configured to build the Spring Boot application into a container:
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
docker-compose.yml
version: '3.9'

services:
  app:
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/your_database_name
      - SPRING_DATASOURCE_USERNAME=your_db_user
      - SPRING_DATASOURCE_PASSWORD=your_db_password
    depends_on:
      - db

  db:
    image: mysql:8.0
    container_name: mysql-container
    ports:
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: root_password
      MYSQL_DATABASE: your_database_name
      MYSQL_USER: your_db_user
      MYSQL_PASSWORD: your_db_password
