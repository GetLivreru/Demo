# Sensor Data REST API Service

## Project Description
This Spring Boot application provides a REST API service for collecting and managing sensor data. The service allows sensors to register, submit measurements (temperature and rainfall data), and retrieve historical measurements. The API is secured using JWT authentication.

## Features
- Sensor registration with duplicate name validation
- Measurement data collection with temperature and rainfall information
- Retrieval of all measurements
- Rainy days count calculation
- JWT-based authentication
- Swagger API documentation
- Docker containerization
- Automated test suite

## Technology Stack
- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- PostgreSQL
- Docker & Docker Compose
- JUnit 5 & Mockito
- Swagger/OpenAPI 3.0

## Getting Started

### Prerequisites
- Docker and Docker Compose installed
- Java 17 or later (for local development)
- Maven (for local development)

### Running with Docker
1. Clone the repository:
```bash
git clone https://github.com/yourusername/sensor-api.git
cd sensor-api
```

2. Start the application using Docker Compose:
```bash
docker-compose up -d
```

The application will be available at `http://localhost:8080`

### Local Development Setup
1. Configure PostgreSQL database settings in `application.properties`
2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

## API Endpoints

### Authentication
All endpoints except registration require a valid JWT token in the Authorization header:
```
Authorization: Bearer <your_jwt_token>
```

### Sensor Registration
```
POST /sensors/registration
Content-Type: application/json

{
    "name": "sensorName"
}
```

### Add Measurement
```
POST /measurements/add
Content-Type: application/json

{
    "value": 23.5,
    "raining": true,
    "sensor": {
        "name": "sensorName"
    }
}
```

### Get All Measurements
```
GET /measurements
```

### Get Rainy Days Count
```
GET /measurements/rainyDaysCount
```

## API Documentation
Swagger documentation is available at `http://localhost:8080/swagger-ui.html` when the application is running.

## Testing
The project includes a comprehensive test suite covering all major functionality:

### Running Tests
```bash
mvn test
```

### Test Coverage
- Controller layer tests
- Service layer tests
- Repository layer tests
- Integration tests
- Security tests

## Client Application
A Java client application is included for testing the API. It performs:
1. Sensor registration
2. Sending 1000 random measurements
3. Retrieving all measurements

### Running the Client
```bash
java -jar sensor-client.jar
```

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/sensor/
│   │       ├── controllers/
│   │       ├── services/
│   │       ├── repositories/
│   │       ├── models/
│   │       ├── dto/
│   │       └── security/
│   └── resources/
│       └── application.properties
├── test/
│   └── java/
└── docker/
    ├── Dockerfile
    └── docker-compose.yml
```

## Security Considerations
- JWT-based authentication
- Input validation
- Rate limiting
- CORS configuration
- Security headers

## Contributing
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License
This project is licensed under the MIT License - see the LICENSE.md file for details.
