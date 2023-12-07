CREATE TABLE IF NOT EXISTS usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS canciones (
    id INT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuario_instrumento_cancion (
    id INT PRIMARY KEY,
    usuario_id INT,
    cancion_id INT,
    instrumento VARCHAR(255) NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (cancion_id) REFERENCES canciones(id)
);

INSERT INTO usuario (nombre) VALUES ('Carlos');
INSERT INTO usuario (nombre) VALUES ('Helena');