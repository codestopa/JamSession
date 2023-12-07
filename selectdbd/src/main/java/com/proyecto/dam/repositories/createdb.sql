CREATE TABLE IF NOT EXISTS usuarios (
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
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (cancion_id) REFERENCES canciones(id)
);
