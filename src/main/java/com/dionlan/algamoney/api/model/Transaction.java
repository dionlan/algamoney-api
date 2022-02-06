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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@NotNull
	private String description;
	
	@NotNull
	@Column(name = "due_date")
	private LocalDate dateDue;
	
	@Column(name = "payment_date")
	private LocalDate datePayment;
	
	@NotNull
	private BigDecimal amount;
	
	private String observation;
	
	@NotNull
	@Column(name = "transaction_type")
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_person")
	@JsonIgnoreProperties("contacts")
	private Person person;
	
	@JsonIgnore
	public boolean isIncome() {
		return TransactionType.RECEITA.equals(transactionType);
	}
}