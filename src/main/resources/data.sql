DROP TABLE IF EXISTS PASSENGER;

CREATE TABLE PASSENGER(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    gender VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    phone VARCHAR(15) DEFAULT NULL
);

INSERT INTO PASSENGER( firstname, lastname, gender, age, phone) VALUES ('Ramazan', 'Sakin', 'male', 27, '905554443322'),
                                                                ('Munise', 'Sakin', 'female', 26, '905554443322'),
                                                                ('Mustafa', 'Sakin', 'male', 14, '905554443322'),
                                                                ('Mustafa', 'Kuytu', 'male', 25, '905554443322');