CREATE DATABASE contactos;
USE contactos;

CREATE TABLE contacto (
    id integer serial NOT NULL,
    nombre character varying(20) NOT NULL,
    telefono character varying(8) NOT NULL,
    direccion character varying(20) NOT NULL,
    PRIMARY KEY(id)
);

