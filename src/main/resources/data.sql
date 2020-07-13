-- Table Schemas
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
    name VARCHAR(20) NOT NULL
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
    quota INT DEFAULT 0,
    price INT DEFAULT 0,
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
    flight_id INT NOT NULL,
    FOREIGN KEY (passenger_id) REFERENCES passenger(id),
    FOREIGN KEY (flight_id) REFERENCES flight(id)
);


-- ////////////////////////////////////////////////////////////////////////////
--  Sample static test values
-- ////////////////////////////////////////////////////////////////////////////
INSERT INTO airport( name )  VALUES     ('Sabiha Gokcen Havalimanı'),
                                        ('Zafer Havalimanı'),
                                        ('Malatya Havalimanı'),
                                        ('Aydın Havalimanı'),
                                        ('Esenboğa Havalimanı'),
                                        ('Erzurum Havalimanı');


INSERT INTO route( departure_airport_id, arrival_airport_id ) VALUES    (1, 3),
                                                                        (2, 4),
                                                                        (1, 4),
                                                                        (5, 6);


INSERT INTO airport_company( name ) VALUES          ('Türk Hava Yolları'),
                                                    ('Pegasus'),
                                                    ('AnadoluJet'),
                                                    ('SampleJet');


INSERT INTO passenger( firstname, lastname, gender, age, phone ) VALUES ( 'Ali', 'Tek', 'male', 27, '905554443322'),
                                                                        ( 'Ceren', 'Telli', 'female', 26, '905554443322'),
                                                                        ( 'Dilek', 'Tutku', 'female', 14, '905554443322'),
                                                                        ( 'Cem', 'Saygın', 'male', 25, '905554443322'),
                                                                        ( 'Semih', 'Sanlı', 'male', 25, '905554443322'),
                                                                        ( 'Veli', 'Telli', 'female', 26, '905554443322'),
                                                                        ( 'Kamil', 'Tutku', 'female', 14, '905554443322'),
                                                                        ( 'Sam', 'Caroline', 'male', 25, '905554443322'),
                                                                        ( 'Jhonny', 'Deepy', 'male', 41, '905554443322'),
                                                                        ( 'Fatih', 'Telli', 'female', 34, '905554443322'),
                                                                        ( 'Semra', 'Kelebek', 'female', 44, '905554443322'),
                                                                        ( 'Mustafa', 'Saygın', 'male', 12, '905554443322'),
                                                                        ( 'Carl', 'Geenny', 'male', 27, '905554443322');


INSERT INTO flight( code, departure_date, estimated_arrival_date, route_id, airport_company_id, quota, price) VALUES    ( 'TCF129', '01.03.2020', '01.03.2020', 3, 1, 45, 30 ),
                                                                                                                        ( 'GDFS12', '01.03.2020', '01.03.2020', 1, 2, 50, 40 ),
                                                                                                                        ( 'ASF1223', '02.03.2020', '02.03.2020', 4, 3, 120, 15 ),
                                                                                                                        ( 'HFDGHF12', '04.03.2020', '12.03.2020', 4, 2, 25, 90 ),
                                                                                                                        ( 'BDASDX12', '05.02.2020', '23.03.2020', 1, 2, 50, 40 ),
                                                                                                                        ( 'GDASFDS34', '23.06.2021', '05.03.2022', 4, 3, 120, 15 ),
                                                                                                                        ( 'GGBAV3463', '11.03.2020', '21.03.2020', 4, 2, 20, 90 );


INSERT INTO ticket(passenger_id, flight_id) VALUES      (1, 1),
                                                        (2, 3),
                                                        (3, 2),
                                                        (4, 2),
                                                        (5, 4),
                                                        (6, 3),
                                                        (7, 2),
                                                        (8, 5),
                                                        (9, 6);

