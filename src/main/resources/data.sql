DROP TABLE IF EXISTS TICKET;

DROP TABLE IF EXISTS PASSENGER;

DROP TABLE IF EXIST AIRPORT;

CREATE TABLE AIRPORT(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE PASSENGER(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    gender VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    phone VARCHAR(15) DEFAULT NULL
);

CREATE TABLE TICKET(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    passenger_id INT NOT NULL,
    price INT NOT NULL,
    FOREIGN KEY (passenger_id) REFERENCES PASSENGER(id)
);

INSERT INTO AIRPORT(id, name)  VALUES   ('Sabiha Gokcen Havalimanı'),
                                        ('Zafer Havalimanı'),
                                        ('Malatya Havalimanı');

INSERT INTO PASSENGER( firstname, lastname, gender, age, phone) VALUES ('Ramazan', 'Sakin', 'male', 27, '905554443322'),
                                                                ('Munise', 'Sakin', 'female', 26, '905554443322'),
                                                                ('Mustafa', 'Sakin', 'male', 14, '905554443322'),
                                                                ('Mustafa', 'Kuytu', 'male', 25, '905554443322');


INSERT INTO TICKET(passenger_id, price) VALUES  (1, 45),
                                                (2, 64);