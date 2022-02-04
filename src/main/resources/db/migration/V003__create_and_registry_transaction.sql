CREATE TABLE transaction (
	id BIGINT NOT NULL AUTO_INCREMENT,
	description VARCHAR(255) NOT NULL,
	date_due DATE NOT NULL,
	date_payment DATE,
	value DECIMAL(10,2) NOT NULL,
	observation VARCHAR(255) DEFAULT NULL,
	type VARCHAR(20) DEFAULT NULL,
	id_category BIGINT NOT NULL,
	id_person BIGINT NOT NULL,
	
	PRIMARY KEY(id),
	
	FOREIGN KEY (id_category) REFERENCES category(id),
	FOREIGN KEY (id_person) REFERENCES person(id)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO transaction (description, date_due, date_payment, value, observation, type, id_category, id_person) values 
("Salário Mensal", "2021-06-10", null, 6500.00, "Distribuição de Lucros", "RECEITA", 1, 1),
("Serviço Voluntário", "2021-06-22", null, 1500.00, "Serviço Voluntário no mês de junho", "RECEITA", 1, 1),
("Academia", "2021-06-10", "2021-06-05", 100.00, "Parcela de Junho", "DESPESA", 1, 1),
("Conta do Celular", "2021-06-10", "2021-06-02", 69.00, "Crédito Celular TIM", "DESPESA", 5, 1),
('Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'RECEITA', 1, 1),
('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'DESPESA', 2, 2),
('Top Club', '2017-06-10', null, 120, null, 'RECEITA', 3, 3),
('CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'RECEITA', 3, 4),
('DMAE', '2017-06-10', null, 200.30, null, 'DESPESA', 3, 5),
('Extra', '2017-03-10', '2017-03-10', 1010.32, null, 'RECEITA', 4, 6),
('Bahamas', '2017-06-10', null, 500, null, 'RECEITA', 1, 7),
('Top Club', '2017-03-10', '2017-03-10', 400.32, null, 'DESPESA', 4, 8),
('Despachante', '2017-06-10', null, 123.64, 'Multas', 'DESPESA', 3, 9),
('Pneus', '2017-04-10', '2017-04-10', 665.33, null, 'RECEITA', 5, 10),
('Café', '2017-06-10', null, 8.32, null, 'DESPESA', 1, 5),
('Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'DESPESA', 5, 4),
('Instrumentos', '2017-06-10', null, 1040.32, null, 'DESPESA', 4, 3),
('Café', '2017-04-10', '2017-04-10', 4.32, null, 'DESPESA', 4, 2),
('Lanche', '2017-06-10', null, 10.20, null, 'DESPESA', 4, 1);