
DROP TABLE IF EXISTS ticket;

DROP TABLE IF EXISTS flight;

DROP TABLE IF EXISTS airport_company;

DROP TABLE IF EXISTS passenger;

DROP TABLE IF EXISTS route;

DROP TABLE IF EXISTS airport;

CREATE TABLE airport(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE passenger(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    firstname VARCHAR(25) NOT NULL,
    lastname VARCHAR(25) NOT NULL,
    gender VARCHAR(25),
    age INT DEFAULT 0,
    phone VARCHAR(15)
);

CREATE TABLE airport_company(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    company_name VARCHAR(20) NOT NULL
);

CREATE TABLE route(
    id INT AUTO_INCREMENT PRIMARY KEY,
    departure_airport_id INT NOT NULL,
    arrival_airport_id INT NOT NULL,
    FOREIGN KEY (departure_airport_id) REFERENCES airport(id),
    FOREIGN KEY (arrival_airport_id) REFERENCES airport(id)
);

CREATE TABLE flight(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    code VARCHAR(10) NOT NULL,
    departure_date VARCHAR(20),
    estimated_arrival_date VARCHAR(20),
    route_id INT NOT NULL,
    airport_company_id INT NOT NULL,
    FOREIGN KEY (route_id) REFERENCES route(id),
    FOREIGN KEY (airport_company_id) REFERENCES airport_company(id)
);

CREATE TABLE ticket(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    passenger_id INT NOT NULL,
    price INT DEFAULT 0,
    flight_id INT NOT NULL,
    FOREIGN KEY (passenger_id) REFERENCES passenger(id),
    FOREIGN KEY (flight_id) REFERENCES flight(id)
);


-- ////////////////////////////////////////////////////////////////////////////
--  Sample static test values
-- ////////////////////////////////////////////////////////////////////////////
INSERT INTO airport( name )  VALUES     ('Sabiha Gokcen Havalimanı'),
                                        ('Zafer Havalimanı'),
                                        ('Malatya Havalimanı' ),
                                        ('Aydın Havalimanı');


INSERT INTO route( departure_airport_id, arrival_airport_id ) VALUES    (1, 3),
                                                                        (2, 4);


INSERT INTO airport_company( company_name ) VALUES  ('Türk Hava Yolları'),
                                                    ('Pegasus');


INSERT INTO passenger( firstname, lastname, gender, age, phone ) VALUES ( 'Ali', 'Tek', 'male', 27, '905554443322'),
                                                                        ( 'Veli', 'Telli', 'female', 26, '905554443322'),
                                                                        ( 'Mustafa', 'Tutku', 'male', 14, '905554443322'),
                                                                        ( 'Cem', 'Saygın', 'male', 25, '905554443322');


INSERT INTO flight( code, departure_date, estimated_arrival_date, route_id, airport_company_id) VALUES  ( 'TCF129', '01.03.2020', '01.03.2020', 1, 1 ),
                                                                                                        ( 'TCF129', '01.03.2020', '01.03.2020', 2, 2 );


INSERT INTO ticket(passenger_id, price, flight_id) VALUES   (1, 45, 1),
                                                            (2, 64, 1),
                                                            (3, 90, 2),
                                                            (4, 45, 2);

