# Airport Reservation System

A comprehensive Spring Boot-based airport reservation system with advanced analytics capabilities. This application provides a complete solution for managing flights, passengers, tickets, and more, with powerful reporting and analytics features.

## 🚀 Features

- **Flight Management**: CRUD operations for flights, routes, and airport companies
- **Passenger Management**: Manage passenger information and bookings
- **Ticket Booking**: Book, cancel, and search for tickets
- **Advanced Analytics**:
  - Route popularity trends
  - Airport connection analysis
  - Passenger demographics and segmentation
  - Flight performance metrics
- **RESTful API**: Fully documented with Swagger/OpenAPI
- **Security**: JWT-based authentication and authorization
- **Data Generation**: Built-in mock data generation for testing

## 🛠️ Tech Stack

- **Backend**: 
  - Java 21
  - Spring Boot 3.2.0
  - Spring Data JPA
  - Hibernate
  - PostgreSQL (Dockerized)
- **Build Tool**: Maven
- **API Documentation**: 
  - Swagger/OpenAPI 3.0
  - SpringDoc OpenAPI UI
- **Containerization**: Docker & Docker Compose
- **Testing**: JUnit 5, Mockito

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- Docker and Docker Compose (for containerized deployment)
- PostgreSQL (if not using Docker)

### Local Development Setup (Without Docker)

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/AirportReservationSystem.git
   cd AirportReservationSystem
   ```

2. **Configure the database**
   - Install PostgreSQL if not already installed
   - Create a new database named `airport_db`
   - Update `application.properties` with your database credentials:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/airport_db
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Build and run the application**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the application**
   - API Documentation: http://localhost:8080/swagger-ui.html
   - H2 Console (if enabled): http://localhost:8080/h2-console

### Docker Setup (Recommended)

1. **Clone the repository** (if not already done)
   ```bash
   git clone https://github.com/ramazansakin/AirportReservationSystem.git
   cd AirportReservationSystem
   ```

2. **Build and start the containers**
   ```bash
   docker-compose up --build
   ```
   This will:
   - Build the Spring Boot application
   - Start a PostgreSQL container
   - Initialize the database with sample data

3. **Access the application**
   - API Documentation: http://localhost:8080/swagger-ui.html
   - Application: http://localhost:8080

## 📊 API Endpoints

### Core Endpoints
- `GET /v1/airports` - List all airports
- `GET /v1/flights` - List all flights
- `GET /v1/passengers` - List all passengers
- `GET /v1/tickets` - List all tickets

### Analytics Endpoints
- `GET /v1/analytics/route-trends` - Get route popularity trends
- `GET /v1/analytics/airport-connections` - Analyze airport connections
- `GET /v1/analytics/passenger-segmentation` - Get passenger demographics
- `GET /v1/analytics/flight-performance` - Get flight performance metrics
- `GET /v1/analytics/busiest-routes` - Get busiest routes
- `GET /v1/analytics/most-profitable-flights` - Get most profitable flights

## 🔧 Configuration

Key configuration options in `application.properties`:

```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:postgresql://postgres:5432/airport_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update

# JWT Configuration
jwt.secret=your-jwt-secret
jwt.expiration=86400000  # 24 hours in milliseconds

# Swagger Configuration
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

## 📦 Data Model

The application uses the following main entities:

- **Airport**: Represents an airport with name and address
- **AirportCompany**: Represents an airline company
- **Route**: Defines a route between two airports
- **Flight**: Represents a scheduled flight on a specific route
- **Passenger**: Contains passenger information
- **Ticket**: Represents a booking for a passenger on a flight

## 📈 Analytics Features

### 1. Route Popularity Trends
Analyze how route popularity changes over time, identifying seasonal patterns and growth trends.

### 2. Airport Connection Analysis
Discover the busiest airport connections and analyze traffic flow between airports.

### 3. Passenger Demographics
Segment passengers by various criteria and analyze travel patterns and preferences.

### 4. Flight Performance Metrics
Monitor flight performance, including occupancy rates, revenue, and operational efficiency.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 👨‍💻 Author

- **Ramazan Sakin** - [GitHub](https://github.com/ramazansakin)

~~## 🙏 Acknowledgments

- Built with ❤️ using Spring Boot
- Special thanks to all contributors~~
