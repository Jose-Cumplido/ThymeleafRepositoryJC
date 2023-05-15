DROP TABLE ranking IF EXISTS;
DROP TABLE especie IF EXISTS;
DROP TABLE familia IF EXISTS;

DROP sequence IF EXISTS hibernate_sequence;

CREATE sequence hibernate_sequence START WITH 100 increment BY 1;

CREATE TABLE familia (
	id BIGINT NOT NULL, 
	destacada BOOLEAN NOT NULL, 
	imagen VARCHAR(512), 
	nombre VARCHAR(512), 
	CONSTRAINT PK_familia PRIMARY KEY (id)
);

CREATE TABLE especie(
	id BIGINT NOT NULL,
	descripcion VARCHAR(MAX),
    imagen VARCHAR (512),
    nombre VARCHAR (512),
	familia_id int,
	CONSTRAINT PK_especie PRIMARY KEY (id),
	CONSTRAINT FK_familiaespecie FOREIGN KEY (familia_id) REFERENCES familia (id)
);

create table ranking (
	id BIGINT NOT NULL AUTO_INCREMENT, 
	fecha TIMESTAMP, 
	puntuacion INTEGER NOT NULL,
	especie_id BIGINT, 
	CONSTRAINT PK_ranking PRIMARY KEY (id),
    CONSTRAINT FK_especieranking FOREIGN KEY (especie_id) REFERENCES especie (id)
);

CREATE INDEX IDX_especienombre ON especie(nombre);
CREATE INDEX IDX_especiedescripcion ON especie(descripcion);
CREATE INDEX IDX_especienombredescripcion ON especie(nombre,descripcion);