-- Schema definition for Airport Reservation System
-- PostgreSQL-compatible schema

-- Drop tables if they exist (in reverse order of dependencies)
DROP TABLE IF EXISTS ticket CASCADE;
DROP TABLE IF EXISTS flight CASCADE;
DROP TABLE IF EXISTS route CASCADE;
DROP TABLE IF EXISTS airport_company CASCADE;
DROP TABLE IF EXISTS passenger CASCADE;
DROP TABLE IF EXISTS airport CASCADE;

-- Create sequence for IDs if they don't exist
CREATE SEQUENCE IF NOT EXISTS airport_id_seq;
CREATE SEQUENCE IF NOT EXISTS passenger_id_seq;
CREATE SEQUENCE IF NOT EXISTS airport_company_id_seq;
CREATE SEQUENCE IF NOT EXISTS route_id_seq;
CREATE SEQUENCE IF NOT EXISTS flight_id_seq;
CREATE SEQUENCE IF NOT EXISTS ticket_id_seq;

-- Airport table
CREATE TABLE airport (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(250) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Passenger table
CREATE TABLE passenger (
    id BIGSERIAL PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    gender VARCHAR(20),
    age INTEGER DEFAULT 0,
    phone VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Airport Company table
CREATE TABLE airport_company (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_airport_company_name UNIQUE (name)
);

-- Route table
CREATE TABLE route (
    id BIGSERIAL PRIMARY KEY,
    departure_airport_id BIGINT NOT NULL,
    arrival_airport_id BIGINT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_route_departure_airport FOREIGN KEY (departure_airport_id) 
        REFERENCES airport(id) ON DELETE CASCADE,
    CONSTRAINT fk_route_arrival_airport FOREIGN KEY (arrival_airport_id) 
        REFERENCES airport(id) ON DELETE CASCADE,
    CONSTRAINT uk_route_airports UNIQUE (departure_airport_id, arrival_airport_id)
);

-- Flight table
CREATE TABLE flight (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    quota INTEGER DEFAULT 0,
    price DECIMAL(10, 2) DEFAULT 0,
    departure_date TIMESTAMP WITH TIME ZONE NOT NULL,
    estimated_arrival_date TIMESTAMP WITH TIME ZONE NOT NULL,
    route_id BIGINT NOT NULL,
    airport_company_id BIGINT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_flight_route FOREIGN KEY (route_id) 
        REFERENCES route(id) ON DELETE CASCADE,
    CONSTRAINT fk_flight_airport_company FOREIGN KEY (airport_company_id) 
        REFERENCES airport_company(id) ON DELETE CASCADE,
    CONSTRAINT chk_flight_dates CHECK (estimated_arrival_date > departure_date)
);

-- Ticket table
CREATE TABLE ticket (
    id BIGSERIAL PRIMARY KEY,
    passenger_id BIGINT NOT NULL,
    flight_id BIGINT NOT NULL,
    seat_number VARCHAR(10),
    booking_reference VARCHAR(20) UNIQUE,
    booking_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'CONFIRMED',
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_ticket_passenger FOREIGN KEY (passenger_id) 
        REFERENCES passenger(id) ON DELETE CASCADE,
    CONSTRAINT fk_ticket_flight FOREIGN KEY (flight_id) 
        REFERENCES flight(id) ON DELETE CASCADE,
    CONSTRAINT uk_ticket_flight_passenger UNIQUE (flight_id, passenger_id)
);

-- Create indexes for better query performance
CREATE INDEX idx_route_airports ON route(departure_airport_id, arrival_airport_id);
CREATE INDEX idx_flight_dates ON flight(departure_date, estimated_arrival_date);
CREATE INDEX idx_flight_route ON flight(route_id);
CREATE INDEX idx_flight_company ON flight(airport_company_id);
CREATE INDEX idx_ticket_flight ON ticket(flight_id);
CREATE INDEX idx_ticket_passenger ON ticket(passenger_id);
CREATE INDEX idx_ticket_booking_ref ON ticket(booking_reference);

-- Add comments for better documentation
COMMENT ON TABLE airport IS 'Stores airport information';
COMMENT ON TABLE passenger IS 'Stores passenger information';
COMMENT ON TABLE airport_company IS 'Stores airline company information';
COMMENT ON TABLE route IS 'Stores routes between airports';
COMMENT ON TABLE flight IS 'Stores flight schedules';
COMMENT ON TABLE ticket IS 'Stores passenger flight bookings';
