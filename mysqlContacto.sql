CREATE DATABASE Contactos;
USE Contactos;

CREATE TABLE contacto (
  id int NOT NULL AUTO_INCREMENT,
  nombre varchar(20) NOT NULL,
  telefono varchar(8) NOT NULL,
  direccion varchar(20) NOT NULL,
  PRIMARY KEY (id)
);
