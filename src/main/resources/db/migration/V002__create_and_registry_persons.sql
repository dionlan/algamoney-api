CREATE TABLE person (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	logradoure VARCHAR(255) DEFAULT NULL,
	number VARCHAR(255) DEFAULT NULL,
	complement VARCHAR(255) DEFAULT NULL,
	district VARCHAR(255) DEFAULT NULL,
	zipcode VARCHAR(255) DEFAULT NULL,
	city VARCHAR(255) DEFAULT NULL,
	state VARCHAR(255) DEFAULT NULL,
	active BOOLEAN DEFAULT NULL,
	
	PRIMARY KEY(id)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO person (name, logradoure, number, complement, district, zipcode, city, state, active) values ('Dionlan Alves', "", "", "", "", "", "", "", true);
INSERT INTO person (name, logradoure, number, complement, district, zipcode, city, state, active) values ('Afrânio de Jesus', "", "", "", "", "", "", "", true);
INSERT INTO person (name, logradoure, number, complement, district, zipcode, city, state, active) values ('Rita Alves', "", "", "", "", "", "", "", true);
INSERT INTO person (name, logradoure, number, complement, district, zipcode, city, state, active) values ('Eduardo Viana', "", "", "", "", "", "", "", true);
INSERT INTO person (name, logradoure, number, complement, district, zipcode, city, state, active) values ('Sebastião Machado', "", "", "", "", "", "", "", true);