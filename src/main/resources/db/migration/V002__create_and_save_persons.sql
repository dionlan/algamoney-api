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

INSERT INTO person (name, logradoure, number, complement, district, zipcode, city, state, active) values 
('João Silva', 'Rua do Abacaxi', '10', null, 'Brasil', '38.400-12', 'Uberlândia', 'MG', true),
('Maria Rita', 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-12', 'Ribeirão Preto', 'SP', true),
('Pedro Santos', 'Rua da Bateria', '23', null, 'Morumbi', '54.212-12', 'Goiânia', 'GO', true),
('Ricardo Pereira', 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-12', 'Salvador', 'BA', true),
('Josué Mariano', 'Av Rio Branco', '321', null, 'Jardins', '56.400-12', 'Natal', 'RN', true),
('Pedro Barbosa', 'Av Brasil', '100', null, 'Tubalina', '77.400-12', 'Porto Alegre', 'RS', true),
('Henrique Medeiros', 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-12', 'Rio de Janeiro', 'RJ', true),
('Carlos Santana', 'Rua da Manga', '433', null, 'Centro', '31.400-12', 'Belo Horizonte', 'MG', true),
('Leonardo Oliveira', 'Rua do Músico', '566', null, 'Segismundo Pereira', '38.400-00', 'Uberlândia', 'MG', true),
('Isabela Martins', 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-12', 'Manaus', 'AM', true);