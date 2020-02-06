
DROP TABLE IF EXISTS ticket;

DROP TABLE IF EXISTS passenger;

DROP TABLE IF EXISTS route;

DROP TABLE IF EXISTS airport;

CREATE TABLE airport(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE route(
    id INT AUTO_INCREMENT PRIMARY KEY,
    departure_airport INT NOT NULL,
    arrival_airport INT NOT NULL,
    FOREIGN KEY (departure_airport) REFERENCES airport(id),
    FOREIGN KEY (arrival_airport) REFERENCES airport(id)
);

CREATE TABLE passenger(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    gender VARCHAR(50),
    age INT DEFAULT 0,
    phone VARCHAR(15),
    ticket_id INT
);

CREATE TABLE ticket(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    passenger INT NOT NULL,
    price INT DEFAULT 0,
    FOREIGN KEY (passenger) REFERENCES passenger(id)
);


INSERT INTO airport( name)  VALUES     ('Sabiha Gokcen Havalimanı'),
                                        ('Zafer Havalimanı'),
                                        ('Malatya Havalimanı' ),
                                        ('Aydın Havalimanı');


INSERT INTO route( departure_airport, arrival_airport ) VALUES  (1, 3),
                                                                (2, 4);


INSERT INTO passenger( firstname, lastname, gender, age, phone ) VALUES ( 'Ali', 'Tek', 'male', 27, '905554443322'),
                                                                        ( 'Veli', 'Telli', 'female', 26, '905554443322'),
                                                                        ( 'Mustafa', 'Tutku', 'male', 14, '905554443322'),
                                                                        ( 'Cem', 'Saygın', 'male', 25, '905554443322');


INSERT INTO ticket(passenger, price) VALUES     (1, 45),
                                                (2, 64),
                                                (3, 90),
                                                (4, 45);
