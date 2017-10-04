CREATE TABLE categoria (
  id serial PRIMARY KEY,
  nombre varchar(50) not null unique  
);

CREATE TABLE articulo (
  id serial PRIMARY KEY,
  nombre varchar(50) not null unique,
  precio decimal(10,2),
  categoria bigint   
);

INSERT INTO categoria (nombre) values ('categoría 1');
INSERT INTO categoria (nombre) values ('categoría 2');
INSERT INTO categoria (nombre) values ('categoría 3');

INSERT INTO articulo (nombre, precio, categoria) values ('artículo 1', 1, 1);
INSERT INTO articulo (nombre, precio, categoria) values ('artículo 2', 2, 2);
INSERT INTO articulo (nombre, precio, categoria) values ('artículo 3', 3, 1);
INSERT INTO articulo (nombre, precio, categoria) values ('artículo 4', 4, 4);
INSERT INTO articulo (nombre) values ('artículo 5');

