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
("Conta do Celular", "2021-06-10", "2021-06-02", 69.00, "Crédito Celular TIM", "DESPESA", 5, 1);