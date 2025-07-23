-- ===========================================
-- Airport Reservation System - Test Data
-- ===========================================
-- This script generates test data for all entities
-- Run with: spring.sql.init.mode=always

-- Clear existing data (if any)
TRUNCATE TABLE ticket, flight, route, airport_company, passenger, airport RESTART IDENTITY CASCADE;

-- ===========================================
-- Insert Airports (200 airports)
-- ===========================================

-- Insert airports with hardcoded values for simplicity
INSERT INTO airport (name, address)
SELECT 
    'Airport ' || n::text,
    'Address ' || n::text || ', City ' || (n % 50 + 1)::text || ', Country ' || (n % 20 + 1)::text
FROM generate_series(1, 200) n;

-- ===========================================
-- Insert Airport Companies (50+ companies)
-- ===========================================

-- Insert airport companies with simple naming pattern
INSERT INTO airport_company (name)
SELECT DISTINCT 'Airline ' || n::text
FROM generate_series(1, 100) n;

-- ===========================================
-- Insert Routes (2,000+ routes)
-- ===========================================

-- Create routes between airports
INSERT INTO route (departure_airport_id, arrival_airport_id)
SELECT 
    a.id as departure_airport_id,
    b.id as arrival_airport_id
FROM 
    (SELECT id FROM airport ORDER BY id) a,
    (SELECT id FROM airport ORDER BY id) b
WHERE 
    a.id != b.id AND
    a.id <= 100 AND b.id <= 100 -- Limit for faster execution
LIMIT 2000;

-- ===========================================
-- Insert Flights (100,000 flights)
-- ===========================================

-- Generate flights with random data - ENSURE estimated_arrival_date is ALWAYS after departure_date
INSERT INTO flight (code, quota, price, departure_date, estimated_arrival_date, route_id, airport_company_id)
SELECT 
    'FL' || LPAD(n::text, 4, '0') as code,
    50 + (n % 450) as quota,
    50 + (n % 950) as price,
    CURRENT_DATE + ((n % 730) || ' days')::interval as departure_date,
    CURRENT_DATE + ((n % 730) || ' days')::interval + ((2 + (n % 10)) || ' hours')::interval as estimated_arrival_date,
    (SELECT id FROM route ORDER BY id OFFSET (n % (SELECT COUNT(*) FROM route)) LIMIT 1) as route_id,
    (SELECT id FROM airport_company ORDER BY id OFFSET (n % (SELECT COUNT(*) FROM airport_company)) LIMIT 1) as airport_company_id
FROM generate_series(1, 100000) n;

-- ===========================================
-- Insert Passengers (100,000 passengers)
-- ===========================================

-- Generate passengers with simple data
INSERT INTO passenger (firstname, lastname, gender, age, phone, email)
SELECT 
    CASE WHEN n % 2 = 0 THEN 'FirstName' || (n % 100)::text ELSE 'FirstName' || (n % 100 + 100)::text END as firstname,
    'LastName' || (n % 200)::text as lastname,
    CASE WHEN n % 2 = 0 THEN 'male' ELSE 'female' END as gender,
    18 + (n % 60) as age,
    '+' || (1000000000 + n)::text as phone,
    CASE 
        WHEN n % 5 != 0 THEN 'user' || n::text || '@example.com'
        ELSE NULL
    END as email
FROM 
    (VALUES 
        (ARRAY['Ali', 'Ahmet', 'Mehmet', 'Mustafa', 'Yusuf', 'Emre', 'Can', 'Burak', 'Eren', 'Kemal',
               'Ayse', 'Fatma', 'Zeynep', 'Elif', 'Merve', 'Selin', 'Sude', 'Ceren', 'Dilara', 'Esra',
               'John', 'Michael', 'David', 'James', 'Robert', 'William', 'Richard', 'Joseph', 'Thomas', 'Daniel',
               'Emma', 'Olivia', 'Ava', 'Isabella', 'Sophia', 'Mia', 'Charlotte', 'Amelia', 'Harper', 'Evelyn']),
        
        (ARRAY['Yilmaz', 'Kaya', 'Demir', 'Sahin', 'Celik', 'Yildiz', 'Yildirim', 'Ozturk', 'Aydin', 'Ozdemir',
               'Arslan', 'Dogan', 'Kilic', 'Aslan', 'Cetin', 'Kara', 'Kurt', 'Ozkan', 'Simsek', 'Yavuz',
               'Smith', 'Johnson', 'Williams', 'Brown', 'Jones', 'Garcia', 'Miller', 'Davis', 'Rodriguez', 'Martinez',
               'Wilson', 'Anderson', 'Taylor', 'Thomas', 'Hernandez', 'Moore', 'Martin', 'Jackson', 'Thompson', 'White']),
        
        (ARRAY['gmail.com', 'yahoo.com', 'hotmail.com', 'outlook.com', 'protonmail.com', 'icloud.com', 'aol.com', 'yandex.com', 'mail.com'])
    ) AS t(first_names, last_names, domains),
    generate_series(1, 100000);

-- 6. Generate tickets (1,000,000+ tickets)
INSERT INTO ticket (passenger_id, flight_id)
SELECT 
    p.id as passenger_id,
    f.id as flight_id
FROM 
    (SELECT id FROM passenger ORDER BY random() LIMIT 1000000) p,
    (SELECT id FROM flight ORDER BY random() LIMIT 1) f
ON CONFLICT DO NOTHING;

-- Update flight dates to ensure estimated_arrival is after departure_date
UPDATE flight
SET estimated_arrival_date = departure_date + (floor(random() * 3) + 1) * interval '1 hour' + (floor(random() * 60) * interval '1 minute')
WHERE estimated_arrival_date <= departure_date;

-- Clean up functions
DROP FUNCTION IF EXISTS random_string(integer);
DROP FUNCTION IF EXISTS random_date(date, date);
DROP FUNCTION IF EXISTS random_phone();
