=========================================================

DROP DATABASE IF EXISTS fondo;
CREATE DATABASE fondo;
USE fondo;

=========================================================

CREATE TABLE usuarios (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          username VARCHAR(50) NOT NULL UNIQUE,
                          password VARCHAR(100) NOT NULL,
                          nombre VARCHAR(100) NOT NULL
);

=========================================================

CREATE TABLE categorias (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            nombre VARCHAR(50) NOT NULL UNIQUE
);

=========================================================

CREATE TABLE prendas (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         color VARCHAR(50),
                         talla VARCHAR(10),
                         usuario_id INT NOT NULL,
                         categoria_id INT NOT NULL,

                         FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
                         FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

=========================================================

CREATE TABLE eventos (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL
);

=========================================================

CREATE TABLE prenda_evento (
                               prenda_id INT NOT NULL,
                               evento_id INT NOT NULL,

                               PRIMARY KEY (prenda_id, evento_id),

                               FOREIGN KEY (prenda_id) REFERENCES prendas(id) ON DELETE CASCADE,
                               FOREIGN KEY (evento_id) REFERENCES eventos(id) ON DELETE CASCADE
);

=========================================================


INSERT INTO usuarios (username, password, nombre) VALUES
                                                      ('ana', '1234', 'Ana Gómez'),
                                                      ('luis', 'abcd', 'Luis López');

=========================================================

INSERT INTO categorias (nombre) VALUES
                                    ('Camiseta'),
                                    ('Pantalón'),
                                    ('Chaqueta'),
                                    ('Vestido');

=========================================================
INSERT INTO eventos (nombre) VALUES
                                 ('Trabajo'),
                                 ('Fiesta'),
                                 ('Cena'),
                                 ('Boda');

=========================================================


INSERT INTO prendas (nombre, color, talla, usuario_id, categoria_id) VALUES
                                                                         ('Camiseta Roja', 'Rojo', 'M', 1, 1),
                                                                         ('Pantalón Azul', 'Azul', '38', 1, 2),
                                                                         ('Chaqueta Negra', 'Negro', 'M', 1, 3);

=========================================================


INSERT INTO prendas (nombre, color, talla, usuario_id, categoria_id) VALUES
                                                                         ('Camiseta Blanca', 'Blanco', 'L', 2, 1),
                                                                         ('Pantalón Gris', 'Gris', '40', 2, 2);


=========================================================

INSERT INTO prenda_evento (prenda_id, evento_id) VALUES
                                                     (1, 2),  -- Camiseta Roja → Fiesta
                                                     (2, 1),  -- Pantalón Azul → Trabajo
                                                     (3, 3);  -- Chaqueta Negra → Cena
