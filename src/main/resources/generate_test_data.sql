-- SQL Script to generate test data for Airport Reservation System
-- This script is designed to be run after the initial schema is created

-- Function to generate random strings
CREATE OR REPLACE FUNCTION random_string(length integer) RETURNS TEXT AS $$
DECLARE
    chars text := 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
    result text := '';
    i integer := 0;
BEGIN
    FOR i IN 1..length LOOP
        result := result || substr(chars, floor(random() * length(chars) + 1)::integer, 1);
    END LOOP;
    RETURN result;
END;
$$ LANGUAGE plpgsql;

-- Function to generate random dates within a range
CREATE OR REPLACE FUNCTION random_date(start_date date, end_date date) RETURNS date AS $$
BEGIN
    RETURN start_date + (random() * (end_date - start_date))::integer;
END;
$$ LANGUAGE plpgsql;

-- Function to generate random phone numbers
CREATE OR REPLACE FUNCTION random_phone() RETURNS text AS $$
BEGIN
    RETURN '90' || lpad(floor(random() * 999999999)::text, 9, '0');
END;
$$ LANGUAGE plpgsql;

-- 1. Generate more airports (200+ airports)
INSERT INTO airport (name, address)
SELECT 
    initcap(concat(
        array['Ataturk', 'Sabiha Gokcen', 'Esenboga', 'Adnan Menderes', 'Antalya', 'Dalaman', 'Izmir', 'Ankara', 'Istanbul', 'Bodrum',
              'Trabzon', 'Adana', 'Gaziantep', 'Kayseri', 'Samsun', 'Erzurum', 'Diyarbakir', 'Van', 'Elazig', 'Malatya',
              'Heathrow', 'Charles de Gaulle', 'Frankfurt', 'Amsterdam', 'Madrid', 'Rome', 'Dubai', 'Doha', 'New York', 'Los Angeles',
              'Chicago', 'Toronto', 'London', 'Paris', 'Berlin', 'Moscow', 'Tokyo', 'Beijing', 'Sydney', 'Singapore', 'Bangkok',
              'Mumbai', 'Cairo', 'Johannesburg', 'Sao Paulo', 'Buenos Aires', 'Mexico City', 'Miami', 'Vancouver', 'Oslo', 'Stockholm']
    )[1 + floor(random() * 50)], ' ', 
    array['International Airport', ' Airport', ' City Airport', ' Metropolitan Airport', ' Regional Airport', ' Airfield']
    [1 + floor(random() * 6)]),
    
    concat(
        array['Istanbul', 'Ankara', 'Izmir', 'Antalya', 'Bodrum', 'Dalaman', 'Trabzon', 'Adana', 'Gaziantep', 'Kayseri',
              'Samsun', 'Erzurum', 'Diyarbakir', 'Van', 'Elazig', 'Malatya', 'London', 'Paris', 'Frankfurt', 'Amsterdam',
              'Madrid', 'Rome', 'Milan', 'Barcelona', 'Vienna', 'Prague', 'Warsaw', 'Budapest', 'Athens', 'Dubai',
              'Doha', 'Abu Dhabi', 'New York', 'Los Angeles', 'Chicago', 'Toronto', 'Vancouver', 'Sydney', 'Melbourne',
              'Auckland', 'Singapore', 'Bangkok', 'Tokyo', 'Seoul', 'Beijing', 'Shanghai', 'Mumbai', 'Delhi', 'Cairo', 'Cape Town']
    )[1 + floor(random() * 50)], ', ',
    
    array['Turkey', 'UK', 'France', 'Germany', 'Netherlands', 'Spain', 'Italy', 'UAE', 'Qatar', 'USA', 'Canada',
          'Australia', 'New Zealand', 'Singapore', 'Thailand', 'Japan', 'South Korea', 'China', 'India', 'Egypt', 'South Africa',
          'Brazil', 'Argentina', 'Mexico', 'Norway', 'Sweden', 'Finland', 'Denmark', 'Switzerland', 'Austria', 'Belgium',
          'Portugal', 'Greece', 'Russia', 'Saudi Arabia', 'Indonesia', 'Malaysia', 'Vietnam', 'Philippines', 'Israel', 'Morocco']
    [1 + floor(random() * 41)]
    )
FROM generate_series(1, 200);

-- 2. Generate more airline companies (50+ companies)
INSERT INTO airport_company (name)
SELECT DISTINCT ON (name) name
FROM (
    SELECT 
        concat(
            array['Fly', 'Air', 'Sky', 'Global', 'Star', 'Blue', 'Red', 'Green', 'Gold', 'Silver', 'Express', 'Jet', 'Wings', 'Eagle', 'Falcon', 'Hawk', 'Owl', 'Osprey', 'Albatross', 'Condor']
            [1 + floor(random() * 20)], ' ',
            array['Airlines', 'Airways', 'Air', 'Aviation', 'Express', 'Jet', 'Wings', 'International', 'Travels', 'Flights', 'Carriers', 'Air Services']
            [1 + floor(random() * 12)]
        ) as name
    FROM generate_series(1, 100)
) t
WHERE name NOT IN (SELECT name FROM airport_company);

-- 3. Generate routes between airports (2,000+ routes)
INSERT INTO route (departure_airport_id, arrival_airport_id)
SELECT DISTINCT ON (dep_id, arr_id)
    dep_id, arr_id
FROM (
    SELECT 
        a1.id as dep_id,
        a2.id as arr_id
    FROM 
        (SELECT id FROM airport ORDER BY random() LIMIT 200) a1,
        (SELECT id FROM airport ORDER BY random() LIMIT 200) a2
    WHERE a1.id != a2.id
) t
LIMIT 2000;

-- 4. Generate flights (10,000+ flights)
INSERT INTO flight (code, quota, price, departure_date, estimated_arrival_date, route_id, airport_company_id)
SELECT 
    upper(substring(md5(random()::text), 1, 2) || floor(random() * 9000 + 1000)::text) as code,
    (50 + floor(random() * 451))::int as quota, -- Between 50-500
    (50 + floor(random() * 951))::int as price, -- Between 50-1000
    random_date('2024-01-01'::date, '2025-12-31'::date) as departure_date,
    random_date('2024-01-01'::date, '2025-12-31'::date) as estimated_arrival_date,
    (SELECT id FROM route ORDER BY random() LIMIT 1) as route_id,
    (SELECT id FROM airport_company ORDER BY random() LIMIT 1) as airport_company_id
FROM generate_series(1, 10000);

-- 5. Generate passengers (100,000+ passengers)
INSERT INTO passenger (firstname, lastname, gender, age, phone, email)
SELECT 
    first_names[1 + floor(random() * array_length(first_names, 1))] as firstname,
    last_names[1 + floor(random() * array_length(last_names, 1))] as lastname,
    CASE WHEN random() < 0.5 THEN 'male' ELSE 'female' END as gender,
    (18 + floor(random() * 60))::int as age, -- Between 18-78
    random_phone() as phone,
    CASE 
        WHEN random() < 0.8 THEN lower(first_names[1 + floor(random() * array_length(first_names, 1))] || 
                                    '.' || 
                                    last_names[1 + floor(random() * array_length(last_names, 1))] || 
                                    floor(random() * 100)::text || 
                                    '@' || 
                                    domains[1 + floor(random() * array_length(domains, 1))])
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
