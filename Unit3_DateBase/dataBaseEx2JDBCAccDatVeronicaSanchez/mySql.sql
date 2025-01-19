CREATE DATABASE ex2JDBC;
CREATE USER 'ex2AlumnJDBC'@'localhost' IDENTIFIED BY 'ex2AlumnJDBC';
GRANT ALL ON ex2JDBC.* TO  'ex2AlumnJDBC'@'localhost';
USE ex2JDBC;
CREATE TABLE Alumn ( 
    enrollment int PRIMARY KEY AUTO_INCREMENT, 
    name varchar(500), 
    mark1Ev DECIMAL(4,2), 
    mark2Ev DECIMAL(4,2));

INSERT INTO Alumn VALUES (0,"Pepe", 6.7, 8.9);
INSERT INTO Alumn VALUES (1,"Marta", 6.7, 8.9);
INSERT INTO Alumn VALUES (2,"Juan", 6.7, 8.9);
INSERT INTO Alumn VALUES (3,"Maria", 6.7, 8.9);
INSERT INTO Alumn VALUES (4,"Jose", 6.7, 8.9);

CREATE TABLE licencias ( 
    ID integer,
    TIPO varchar(2),
    EXPEDICION datetime,
    CADUCIDAD datetime);

ALTER TABLE licencias
ADD PRIMARY KEY (ID,TIPO);

ALTER TABLE licencias
ADD FOREIGN KEY (ID) REFERENCES Alumn(enrollment);