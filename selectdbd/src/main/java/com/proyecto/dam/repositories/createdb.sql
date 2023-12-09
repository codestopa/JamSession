use test;
create database if not exists JamSession;
use JamSession;

drop table participa;
drop table canciones;
drop table usuario;

CREATE TABLE IF NOT EXISTS usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS canciones (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS participa (
    id INT PRIMARY KEY,
    usuario_id INT,
    cancion_id INT,
    instrumento VARCHAR(255) NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (cancion_id) REFERENCES canciones(id)
);

INSERT INTO usuario (nombre) VALUES ('Carlos');
INSERT INTO usuario (nombre) VALUES ('El Pro');

INSERT INTO canciones (nombre) VALUES ('Scar Tissue');
INSERT INTO canciones (nombre) VALUES ('El Vampiro');