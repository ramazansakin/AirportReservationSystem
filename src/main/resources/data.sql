-- Table Schemas
DROP TABLE IF EXISTS ticket;

DROP TABLE IF EXISTS flight;

DROP TABLE IF EXISTS airport_company;

DROP TABLE IF EXISTS passenger;

DROP TABLE IF EXISTS route;

DROP TABLE IF EXISTS airport;

CREATE TABLE airport(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(250) NOT NULL
);

CREATE TABLE passenger(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    firstname VARCHAR(25) NOT NULL,
    lastname VARCHAR(25) NOT NULL,
    gender VARCHAR(10),
    age INT DEFAULT 0,
    phone VARCHAR(15),
    email VARCHAR(25) UNIQUE
);

CREATE TABLE airport_company(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE route(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    departure_airport_id INT NOT NULL,
    arrival_airport_id INT NOT NULL,
    FOREIGN KEY (departure_airport_id) REFERENCES airport(id),
    FOREIGN KEY (arrival_airport_id) REFERENCES airport(id)
);

CREATE TABLE flight(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    code VARCHAR(10) NOT NULL,
    quota INT DEFAULT 0,
    price INT DEFAULT 0,
    departure_date DATE NOT NULL,
    estimated_arrival_date DATE NOT NULL,
    route_id INT NOT NULL,
    airport_company_id INT NOT NULL,
    FOREIGN KEY (route_id) REFERENCES route(id),
    FOREIGN KEY (airport_company_id) REFERENCES airport_company(id)
);

CREATE TABLE ticket(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    passenger_id INT NOT NULL,
    flight_id INT NOT NULL,
    FOREIGN KEY (passenger_id) REFERENCES passenger(id),
    FOREIGN KEY (flight_id) REFERENCES flight(id)
);


-- ////////////////////////////////////////////////////////////////////////////
--  Sample static test values
-- ////////////////////////////////////////////////////////////////////////////
INSERT INTO airport( name, address )  VALUES    ('Sabiha Gokcen Havalimanı', 'Pendik/Istanbul'),
                                                ('Zafer Havalimanı', 'X Cad/Kütahya'),
                                                ('Malatya Havalimanı', 'Y Cad/Malatya,'),
                                                ('Aydın Havalimanı', 'Z Cad/Aydın'),
                                                ('Esenboğa Havalimanı', 'Akyurt/Ankara'),
                                                ('Erzurum Havalimanı', 'T Cad/Erzurum');


INSERT INTO route( departure_airport_id, arrival_airport_id ) VALUES    (1, 3),
                                                                        (2, 4),
                                                                        (1, 4),
                                                                        (5, 6);


INSERT INTO airport_company( name ) VALUES          ('Türk Hava Yolları'),
                                                    ('Pegasus'),
                                                    ('AnadoluJet'),
                                                    ('SampleJet');


INSERT INTO passenger( firstname, lastname, gender, age, phone, email ) VALUES ( 'Ali', 'Tek', 'male', 27, '905554443322', 'ali@gmail.com'),
                                                                        ( 'Ceren', 'Telli', 'female', 26, '905554443322', null),
                                                                        ( 'Dilek', 'Tutku', 'female', 14, '905554443322', null),
                                                                        ( 'Cem', 'Saygın', 'male', 25, '905554443322', null),
                                                                        ( 'Semih', 'Sanlı', 'male', 25, '905554443322', null),
                                                                        ( 'Veli', 'Telli', 'female', 26, '905554443322', 'veli@hotmail.com'),
                                                                        ( 'Kamil', 'Tutku', 'female', 14, '905554443322', null),
                                                                        ( 'Sam', 'Caroline', 'male', 25, '905554443322', null),
                                                                        ( 'Jhonny', 'Deepy', 'male', 41, '905554443322', 'jhonny@gmail.com'),
                                                                        ( 'Fatih', 'Telli', 'female', 34, '905554443322', null),
                                                                        ( 'Semra', 'Kelebek', 'female', 44, '905554443322', null),
                                                                        ( 'Mustafa', 'Saygın', 'male', 12, '905554443322', 'musti@daddy.com'),
                                                                        ( 'Carl', 'Geenny', 'male', 27, '905554443322', null);


INSERT INTO flight( code, departure_date, estimated_arrival_date, route_id, airport_company_id, quota, price) VALUES    ( 'TCF129', '2020-03-01', '2020-03-04', 3, 1, 45, 30 ),
                                                                                                                        ( 'GDFS12', '2020-03-01', '2020-03-05', 1, 2, 50, 40 ),
                                                                                                                        ( 'ASF1223', '2020-03-01', '2020-03-14', 4, 3, 120, 15 ),
                                                                                                                        ( 'HFDGHF12', '2020-03-01', '2020-03-15', 4, 2, 25, 90 ),
                                                                                                                        ( 'BDASDX12', '2020-03-01', '2020-07-01', 1, 2, 50, 40 ),
                                                                                                                        ( 'GDASFDS34', '2020-03-01', '2020-04-01', 4, 3, 120, 15 ),
                                                                                                                        ( 'GGBAV3463', '2020-03-01', '2020-05-01', 4, 2, 20, 90 );


INSERT INTO ticket(passenger_id, flight_id) VALUES      (1, 1),
                                                        (2, 3),
                                                        (3, 2),
                                                        (4, 2),
                                                        (5, 4),
                                                        (6, 3),
                                                        (7, 2),
                                                        (8, 5),
                                                        (9, 6);

