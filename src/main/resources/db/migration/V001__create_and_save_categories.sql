CREATE TABLE category (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	
	PRIMARY KEY(id)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO category (name) values ('Lazer');
INSERT INTO category (name) values ('Alimentação');
INSERT INTO category (name) values ('Supermercado');
INSERT INTO category (name) values ('Farmácia');
INSERT INTO category (name) values ('Outros');
