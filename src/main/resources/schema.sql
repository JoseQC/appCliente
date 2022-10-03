create  table IF NOT EXISTS `cliente` (
    id bigint auto_increment primary key,
    nombre varchar(255),
    apellido varchar(255),
    edad integer,
    fecha_nacimiento DATE
);

INSERT INTO cliente(nombre, apellido, edad, fecha_nacimiento) VALUES ('Jose', 'Quispe', 32,'1990-01-13');
INSERT INTO cliente(nombre, apellido, edad, fecha_nacimiento) VALUES ('Luis', 'Quispe', 32,'1990-01-13');
INSERT INTO cliente(nombre, apellido, edad, fecha_nacimiento) VALUES ('Pepe', 'Quispe', 32,'1990-01-13');
INSERT INTO cliente(nombre, apellido, edad, fecha_nacimiento) VALUES ('Lucho', 'Quispe', 32,'1990-01-13');
INSERT INTO cliente(nombre, apellido, edad, fecha_nacimiento) VALUES ('Jose Luis', 'Quispe', 32,'1990-01-13');
INSERT INTO cliente(nombre, apellido, edad, fecha_nacimiento) VALUES ('Pepe Lucho', 'Quispe', 32,'1990-01-13');
