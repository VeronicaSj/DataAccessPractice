CREATE DATABASE ex1JDBC;
CREATE USER 'ex1AlumnJDBC'@'localhost' IDENTIFIED BY 'ex1AlumnJDBC';
GRANT ALL ON ex1JDBC.* TO  'ex1AlumnJDBC'@'localhost';
USE ex1JDBC;
CREATE TABLE Alumn ( 
    enrollment int PRIMARY KEY, 
    name varchar(500), 
    mark1Ev DECIMAL(4,2), 
    mark2Ev DECIMAL(4,2));

INSERT INTO Alumn VALUES (0,"Pepe", 6.7, 8.9);
INSERT INTO Alumn VALUES (1,"Marta", 6.7, 8.9);
INSERT INTO Alumn VALUES (2,"Juan", 6.7, 8.9);
INSERT INTO Alumn VALUES (3,"Maria", 6.7, 8.9);
INSERT INTO Alumn VALUES (4,"Jose", 6.7, 8.9);

