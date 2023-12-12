use JamSession;

drop table if exists participa;
drop table if exists canciones;
drop table if exists usuario;

CREATE TABLE IF NOT EXISTS usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS canciones (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    artista VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS participa (
    id INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT,
    cancion_id INT,
    instrumento VARCHAR(255) NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (cancion_id) REFERENCES canciones(id)
);


INSERT INTO canciones (nombre, artista) VALUES ('Under The Bridge','Red Hot Chili Peppers');
INSERT INTO canciones (nombre, artista) VALUES ('El Vampiro','Tabletom');
INSERT INTO canciones (nombre, artista) VALUES ('Hotel California','The Eagles');
INSERT INTO canciones (nombre, artista) VALUES ('Virtual Insanity','Jamiroquai');

