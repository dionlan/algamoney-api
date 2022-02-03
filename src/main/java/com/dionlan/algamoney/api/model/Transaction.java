package com.dionlan.algamoney.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * classe de lançamentos
 * @author dius_
 *
 */
@Data
@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	
	@Column(name = "date_due")
	private LocalDate dateDue;
	
	@Column(name = "date_payment")
	private LocalDate datePayment;
	
	private BigDecimal value;
	
	private String observation;
	
	@Enumerated(EnumType.STRING)
	private TransactionType type;
	
	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "id_person")
	private Person person;
	
}
